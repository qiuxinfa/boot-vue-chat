package com.qxf.controller;


import com.qxf.entity.SysUser;
import com.qxf.service.ChatMessageDetailService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 聊天消息详情 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/message/detail")
public class ChatMessageDetailController {

    @Autowired
    private ChatMessageDetailService detailService;

    // 离线消息
    @GetMapping("/offline")
    public ResultUtil getUserMsg(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultUtil.ok(detailService.getOfflineMsg(null,user.getId(),null));
    }

}
