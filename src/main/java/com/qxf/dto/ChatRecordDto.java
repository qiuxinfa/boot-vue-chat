package com.qxf.dto;

import java.util.Date;

/**
 * @ClassName ChatRecordDto
 * @Description 聊天记录参数传递对象
 * @Author qiuxinfa
 * @Date 2021/2/6 17:03
 **/
public class ChatRecordDto {
    // 消息类型，1群聊，2是私聊
    private Integer msgType;
    // 消息发送者
    private String fromUserId;
    // 消息接收者
    private String toUserId;
    // 群聊id
    private String roomId;
    // 要查询的聊天记录范围
    private Integer days;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

}
