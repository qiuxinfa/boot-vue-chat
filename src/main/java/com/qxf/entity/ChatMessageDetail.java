package com.qxf.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 聊天消息详情
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public class ChatMessageDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 消息发送时间
     */
    private LocalDateTime createTime;

    /**
     * 发送人id
     */
    private String fromUserId;

    /**
     * 发送人姓名
     */
    private String fromUsername;

    /**
     * 发送人头像地址
     */
    private String fromAvatar;

    /**
     * 接收人id
     */
    @TableField(exist = false)
    private String toUserId;

    /**
     * 消息类型，1群聊，2私聊
     */
    private Integer msgType;

    /**
     * 消息内容类型，1文本消息，2图片消息，3语音，4视频，5其他文件
     */
    private Integer contentType;

    /**
     * 群聊id
     */
    private String roomId;

    /**
     * 消息内容
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
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
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }
    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        return "ChatMessageDetail{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", fromUserId=" + fromUserId +
            ", fromUsername=" + fromUsername +
            ", fromAvatar=" + fromAvatar +
            ", msgType=" + msgType +
            ", contentType=" + contentType +
            ", roomId=" + roomId +
            ", content=" + content +
        "}";
    }
}
