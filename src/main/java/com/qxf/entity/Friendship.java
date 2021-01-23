package com.qxf.entity;

import java.io.Serializable;

/**
 * <p>
 * 好友关系
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public class Friendship implements Serializable {

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
     * 好友id
     */
    private String friendId;

    /**
     * 给好友的备注信息
     */
    private String remark;

    /**
     * 分组id
     */
    private String groupId;

    /**
     * 好友状态，0发送添加好友请求，1正式成为好友
     */
    private Integer status;

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
    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friendship{" +
            "id=" + id +
            ", userId=" + userId +
            ", friendId=" + friendId +
            ", remark=" + remark +
            ", groupId=" + groupId +
            ", status=" + status +
        "}";
    }
}
