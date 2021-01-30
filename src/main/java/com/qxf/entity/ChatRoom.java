package com.qxf.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 群聊
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public class ChatRoom implements Serializable {

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
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 群聊名称
     */
    private String name;

    /**
     * 群聊头像地址
     */
    private String avatar;

    /**
     * 群主的userId
     */
    private String masterId;

    /**
     * 管理员id，多个用英文逗号分割，最多不超过6个
     */
    private String managerIds;

    // 群聊用户id，用英文逗号分割
    @TableField(exist = false)
    private String userIds;

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
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
    public String getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(String managerIds) {
        this.managerIds = managerIds;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", name=" + name +
            ", avatar=" + avatar +
            ", masterId=" + masterId +
            ", managerIds=" + managerIds +
        "}";
    }
}
