//package com.qxf.controller;
//
//import com.github.binarywang.java.emoji.EmojiConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import java.util.UUID;
//
///**
// * @ClassName ChatController
// * @Description 聊天
// * @Author qiuxinfa
// * @Date 2021/1/21 20:41
// **/
//@Controller
//public class ChatController {
//    private EmojiConverter emojiConverter = EmojiConverter.getInstance();
//
//    @Autowired
//    SimpMessagingTemplate simpMessagingTemplate;
//
//    /**
//     * 单聊的消息的接受与转发
//     * @param message
//     */
//    @MessageMapping("/ws/chat")
//    public void handleMessage(ChatMessage message){
//        message.setId(UUID.randomUUID().toString().replaceAll("-",""));
//        //处理emoji内容,转换成unicode编码
//        message.setContent(emojiConverter.toHtml(message.getContent()));
//        simpMessagingTemplate.convertAndSendToUser(message.getToUserId(),"/chat",message);
//    }
//
//    /**
//     * 群聊的消息接受与转发
//     * @param groupMsgContent
//     */
//    @MessageMapping("/ws/groupChat")
//    public void handleGroupMessage(ChatMessage groupMsgContent){
//        //处理emoji内容,转换成unicode编码
//        groupMsgContent.setContent(emojiConverter.toHtml(groupMsgContent.getContent()));
//        //保证来源正确性，从Security中获取用户信息
//        groupMsgContent.setId(UUID.randomUUID().toString().replaceAll("-",""));
//        //转发该条数据
//        simpMessagingTemplate.convertAndSend("/topic/rooms/"+groupMsgContent.getRoomId(),groupMsgContent);
//    }
//
//}
