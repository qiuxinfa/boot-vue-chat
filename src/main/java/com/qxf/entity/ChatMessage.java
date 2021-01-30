package com.qxf.entity;

import java.io.Serializable;

/**
 * <p>
 * 聊天消息
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 接收人id
     */
    private String toUserId;

    /**
     * 消息详情id
     */
    private String detailId;

    /**
     * 是否已读，0未读，1已读
     */
    private Integer isRead;

    public ChatMessage() { }

    public ChatMessage(String id, String toUserId, String detailId, Integer isRead) {
        this.id = id;
        this.toUserId = toUserId;
        this.detailId = detailId;
        this.isRead = isRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
            "id=" + id +
            ", toUserId=" + toUserId +
            ", detailId=" + detailId +
            ", isRead=" + isRead +
        "}";
    }
}
