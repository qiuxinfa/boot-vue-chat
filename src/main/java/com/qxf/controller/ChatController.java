package com.qxf.controller;

import com.github.binarywang.java.emoji.EmojiConverter;
import com.qxf.dto.ChatMessage;
import com.qxf.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import java.util.Date;

/**
 * @ClassName ChatController
 * @Description 聊天
 * @Author qiuxinfa
 * @Date 2021/1/21 20:41
 **/
@Controller
public class ChatController {
    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 单聊的消息的接受与转发
     * @param authentication
     * @param message
     */
    @MessageMapping("/ws/chat")
    public void handleMessage(Authentication authentication, ChatMessage message){
        SysUser user= ((SysUser) authentication.getPrincipal());
        message.setFromUsername(user.getUsername());
        message.setFromUserId(user.getId());
        message.setCreateTime(new Date());
        simpMessagingTemplate.convertAndSendToUser(message.getToUserId(),"/queue/chat",message);
    }

    /**
     * 群聊的消息接受与转发
     * @param authentication
     * @param groupMsgContent
     */
    @MessageMapping("/ws/groupChat")
    public void handleGroupMessage(Authentication authentication, ChatMessage groupMsgContent){
        SysUser currentUser= (SysUser) authentication.getPrincipal();
        //处理emoji内容,转换成unicode编码
        groupMsgContent.setContent(emojiConverter.toHtml(groupMsgContent.getContent()));
        //保证来源正确性，从Security中获取用户信息
        groupMsgContent.setFromUserId(currentUser.getId());
        groupMsgContent.setFromUsername(currentUser.getUsername());
        groupMsgContent.setFromAvatar(currentUser.getAvatar());
        groupMsgContent.setCreateTime(new Date());
        //转发该条数据
        simpMessagingTemplate.convertAndSend("/topic/greetings",groupMsgContent);
    }

}
