package com.qxf.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 聊天记录
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public class ChatRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 消息类型，1群聊消息，2私聊消息
     */
    private Integer msgType;

    /**
     * 群聊id
     */
    private String roomId;

    /**
     * 发送消息的用户id
     */
    private String fromUserId;

    /**
     * 接收消息的用户id
     */
    private String toUserId;

    /**
     * 0文本，1图片，2文件，3语音
     */
    private Integer contentType;

    /**
     * 聊天内容
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
    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatRecord{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", msgType=" + msgType +
            ", roomId=" + roomId +
            ", fromUserId=" + fromUserId +
            ", toUserId=" + toUserId +
            ", contentType=" + contentType +
            ", content=" + content +
        "}";
    }
}
