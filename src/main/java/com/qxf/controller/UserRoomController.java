package com.qxf.controller;


import com.qxf.entity.SysUser;
import com.qxf.service.UserRoomService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户与群聊 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/room")
public class UserRoomController {
    @Autowired
    private UserRoomService service;

    @GetMapping("/list")
    public ResultUtil getRoomList(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication();
        return ResultUtil.ok(service.getRoomList(user.getId()));
    }
}
