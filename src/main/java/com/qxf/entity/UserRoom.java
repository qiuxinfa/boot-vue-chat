package com.qxf.entity;

import com.baomidou.mybatisplus.annotation.TableField;

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

    // 群聊名称
    private String roomName;

    /**
     * 用户角色，1群主2管理员3普通成员
     */
    private Integer userRole;

    // 群聊用户id，用英文逗号分割
    @TableField(exist = false)
    private String userIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

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
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRoom{" +
            "id=" + id +
            ", userId=" + userId +
            ", roomId=" + roomId +
            ", roomName=" + roomName +
            ", userRole=" + userRole +
        "}";
    }
}
