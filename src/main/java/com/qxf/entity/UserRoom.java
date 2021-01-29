package com.qxf.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户与群聊
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public class UserRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 群聊id
     */
    private String roomId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "UserRoom{" +
            "id=" + id +
            ", userId=" + userId +
            ", roomId=" + roomId +
        "}";
    }
}
