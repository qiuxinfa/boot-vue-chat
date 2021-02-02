package com.qxf.controller;


import com.qxf.entity.SysUser;
import com.qxf.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 聊天消息 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/message")
public class ChatMessageController {
    @Autowired
    private ChatMessageService messageService;

    @PostMapping("/update")
    public void updateMsgState(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messageService.updateMsgState(user.getId());
    }
}
