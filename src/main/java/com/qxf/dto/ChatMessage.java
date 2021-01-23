package com.qxf.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ChatMessage
 * @Description 聊天内容实体对象
 * @Author qiuxinfa
 * @Date 2021/1/21 20:44
 **/
public class ChatMessage implements Serializable{
    // 消息唯一id
    private String msgId;
    // 消息发送时间
    private Date createTime;
    // 发送人id
    private String fromUserId;
    // 发送人姓名
    private String fromUsername;
    // 发送人头像地址
    private String fromAvatar;
    // 接收人id
    private String toUserId;
    // 接收人姓名
    private String toUsername;
    // 消息类型，1群聊，2私聊
    private String msgType;
    // 消息内容
    private String content;
    // 群聊id
    private String roomId;
    // 群名
    private String roomName;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "msgId='" + msgId + '\'' +
                ", createTime=" + createTime +
                ", fromUserId='" + fromUserId + '\'' +
                ", fromUsername='" + fromUsername + '\'' +
                ", fromAvatar='" + fromAvatar + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", toUsername='" + toUsername + '\'' +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
