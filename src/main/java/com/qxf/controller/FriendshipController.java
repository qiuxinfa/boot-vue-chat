package com.qxf.controller;


import com.qxf.entity.SysUser;
import com.qxf.service.FriendshipService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 好友关系 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/friend")
public class FriendshipController {
    @Autowired
    private FriendshipService service;

    @GetMapping("/list")
    public ResultUtil getFriendList(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication();
        return ResultUtil.ok(service.getFriendList(user.getId()));
    }
}
