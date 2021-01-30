package com.qxf.controller;

import com.github.binarywang.java.emoji.EmojiConverter;
import com.qxf.entity.ChatMessage;
import com.qxf.entity.ChatMessageDetail;
import com.qxf.entity.SysUser;
import com.qxf.service.ChatMessageDetailService;
import com.qxf.service.ChatMessageService;
import com.qxf.util.RedisUtil;
import com.qxf.util.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

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

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ChatMessageService messageService;

    @Autowired
    private ChatMessageDetailService messageDetailService;

    /**
     * 单聊的消息的接受与转发
     * @param message
     */
    @MessageMapping("/ws/chat")
    public void handleMessage(ChatMessageDetail message){
        message.setId(UUID.randomUUID().toString().replaceAll("-",""));
        //处理emoji内容,转换成unicode编码
        message.setContent(emojiConverter.toHtml(message.getContent()));
        // 保存消息
        this.saveChatMessage(message.getToUserId(),message.getId(),0);
        // 保存消息详情
        messageDetailService.save(message);
        // 如果接收消息的用户在线，推送消息
        if (UserConstant.ONLINE.equals(redisUtil.isOnline(message.getToUserId()))){
            simpMessagingTemplate.convertAndSendToUser(message.getToUserId(),"/chat",message);
        }
    }

    /**
     * 群聊的消息接受与转发
     * @param groupMsgContent
     */
    @MessageMapping("/ws/groupChat")
    public void handleGroupMessage(ChatMessageDetail groupMsgContent){
        String detailId = UUID.randomUUID().toString().replaceAll("-","");
        String roomId = groupMsgContent.getRoomId();
        //处理emoji内容,转换成unicode编码
        groupMsgContent.setContent(emojiConverter.toHtml(groupMsgContent.getContent()));
        groupMsgContent.setId(detailId);
        // 获取群聊用户在线状态
        List<SysUser> userList = redisUtil.getRoomUserState(roomId);
        String fromUserId = groupMsgContent.getFromUserId();
        if (userList != null && userList.size() > 0){
            for (SysUser user : userList){
                if (fromUserId.equals(user.getId())){
                    // 发消息的人，保存消息为已读
                    this.saveChatMessage(user.getId(),detailId,1);
                }else {
                    // 其他人，保存消息为未读
                    this.saveChatMessage(user.getId(),detailId,0);
                    if (user.getIsOnline() == 1){
                        //转发该条数据给在线用户
                        simpMessagingTemplate.convertAndSend("/topic/rooms/"+roomId+"/"+user.getId(),groupMsgContent);
                    }
                }
            }
        }
    }

    private void saveChatMessage(String toUserId,String detailId,Integer isRead){
        ChatMessage chatMessage = new ChatMessage(UUID.randomUUID().toString().replaceAll("-",""),toUserId,detailId,isRead);
        messageService.save(chatMessage);
    }
}
