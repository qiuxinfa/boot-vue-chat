package com.qxf.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.code.kaptcha.Producer;
import com.qxf.dto.UserDto;
import com.qxf.entity.SysUser;
import com.qxf.service.SysUserService;
import com.qxf.util.MailUtil;
import com.qxf.util.RedisUtil;
import com.qxf.util.ResultUtil;
import com.qxf.util.VerifyCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/23 9:54
 **/
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Resource
    private SysUserService userService;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    // 验证码
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 登录
    @PostMapping("/login")
    public ResultUtil login(HttpServletRequest request,@RequestBody SysUser user){
        // 验证码校验
        if (!VerifyCodeValidator.validate(request,user.getCode())){
            return ResultUtil.error("验证码不正确或已过期！");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            // 身份认证
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            SysUser sysUser = (SysUser) authentication.getPrincipal();
            // 更新用户状态为在线
            sysUser.setIsOnline(1);
            // 认证成功，返回用户信息
            return ResultUtil.ok("登录成功！",sysUser);
        }catch (LockedException | DisabledException e){
            e.printStackTrace();
            return ResultUtil.ok("账号被锁定了，请联系管理员解锁！");
        }catch (AuthenticationException e){
            e.printStackTrace();
            return ResultUtil.ok("用户名或密码错误！");
        }
    }

    // 登出
    @GetMapping("/logout")
    public ResultUtil logout(Authentication auth){
        SecurityContextHolder.clearContext();
        return ResultUtil.ok("退出成功！");
    }

    // 注册
    @PostMapping("/register")
    public ResultUtil register(@RequestBody SysUser user){
        Integer register = userService.register(user);
        if (register > 0){
            mailUtil.sendMailAfterRegister(user.getEmail(),user.getUsername()+"，欢迎使用Web聊天系统！",
                    "请妥善保管好邮箱，如果忘记密码，可以通过邮箱重置密码！");
            return ResultUtil.ok("注册成功！");
        }else {
            return ResultUtil.error("注册失败！");
        }

    }

    // 返回新的验证码给前端
    @GetMapping("/verifyCode")
    public void verificationCode(HttpServletResponse response, HttpSession session){
        String code = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(code);
        session.setAttribute("verify_code",code);
        session.setAttribute("verify_code_time", LocalDateTime.now());
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据邮箱查找用户信息
    @PostMapping("/checkEmail")
    public ResultUtil checkEmail(@RequestBody UserDto userDto){
        if (StringUtils.isEmpty(userDto.getEmail())){
            return ResultUtil.error("邮箱不能为空！");
        }
        LambdaQueryWrapper<SysUser> query = Wrappers.lambdaQuery();
        query.eq(SysUser::getUsername,userDto.getUsername());
        query.eq(SysUser::getEmail,userDto.getEmail());
        SysUser user = userService.getOne(query);
        if (user == null){
            return ResultUtil.error("用户名或邮箱不正确！");
        }else {
            // 为什么选择用密码作为key？因为密码是被加密过的，并且无法还原
            String key = user.getPassword();
            redisUtil.remove(key);
            redisUtil.set(key,user);
            // 生成一个链接，跳转到重置密码
            String url = "http://localhost:8080?key="+key;
            mailUtil.sendMailAfterRegister(user.getEmail(),"重置密码","请点击链接，进行重置密码："+url);
            return ResultUtil.ok("重置密码的链接已发送到你的邮箱，15分钟内有效！");
        }


    }

    // 重置密码
    @PostMapping("/resetPassword")
    public ResultUtil resetPassword(@RequestBody UserDto userDto){
        String password = userDto.getPassword();
        String confirmPassword = userDto.getConfirmPassword();
        if (!Objects.equals(password,confirmPassword)){
            return ResultUtil.error("确认密码与密码不一致！");
        }
        // 返回的是 SysUser 对象
        Object o = redisUtil.get(userDto.getKey());
        if (o == null){
            return ResultUtil.error("凭证已失效！");
        }
        // 更新密码
        SysUser user = (SysUser) o;
        user.setPassword(passwordEncoder.encode(password));
        userService.updateById(user);
        // 移除缓存
        redisUtil.remove(userDto.getKey());
        return ResultUtil.ok("密码重置成功，请重新登录！");
    }


}
