package com.qxf.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qxf.entity.SysUser;
import com.qxf.service.SysUserService;
import com.qxf.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    
    @Resource
    private SysUserService userService;

    // 获取所有用户:isFriend=1，表示查询好友，isFriend=0，表示查询非好友
    @GetMapping("/list")
    public ResultUtil getUserList(String searchKey,Integer isFriend){
        if (isFriend == 0 && StringUtils.isEmpty(searchKey)){
            return ResultUtil.error("查询关键字不能为空");
        }
        return ResultUtil.ok(userService.queryUserList(searchKey,isFriend));
    }

    /**
     * 注册操作，检查用户名是否已被注册
     * @param username
     * @return
     */
    @GetMapping("/checkUsername")
    public Integer checkUsername(@RequestParam("username")String username){
        return userService.checkUsername(username);
    }
    
    // 修改个人信息
    @PostMapping("/update")
    public ResultUtil updateUser(@RequestBody SysUser user){
        Integer cnt = userService.updateUser(user);
        if (cnt > 0){
            return ResultUtil.ok("操作成功！");
        }else {
            return ResultUtil.ok("操作失败！");
        }
    }
}
