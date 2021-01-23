package com.qxf.controller;

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

    // 获取所有用户
    @GetMapping("/list")
    public ResultUtil getUserList(){
        return ResultUtil.ok(userService.queryUserList(null));
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

}
