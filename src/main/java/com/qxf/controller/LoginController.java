package com.qxf.controller;

import com.google.code.kaptcha.Producer;
import com.qxf.entity.SysUser;
import com.qxf.service.SysUserService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    SimpMessagingTemplate simpMessagingTemplate;

    // 登录
    @PostMapping("/login")
    public ResultUtil login(String username, String password){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            // 身份认证
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //广播系统通知消息
            simpMessagingTemplate.convertAndSend("/topic/notification","系统消息：用户【"+username+"】进入了聊天室");
            // 认证成功，返回用户信息
            return ResultUtil.ok("登录成功！",authentication.getPrincipal());
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
        SysUser user  = (SysUser)auth.getPrincipal();
        //广播系统消息
        simpMessagingTemplate.convertAndSend("/topic/notification","系统消息：用户【"+user.getUsername()+"】退出了聊天室");
        SecurityContextHolder.clearContext();
        return ResultUtil.ok("退出成功！");
    }

    // 注册
    @PostMapping("/register")
    public ResultUtil register(@RequestBody SysUser user){
        Integer register = userService.register(user);
        if (register > 0){
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
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
