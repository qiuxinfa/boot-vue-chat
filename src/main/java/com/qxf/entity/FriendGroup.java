package com.qxf.entity;

import java.io.Serializable;

/**
 * <p>
 * 好友分组
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public class FriendGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 分组名称
     */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FriendGroup{" +
            "id=" + id +
            ", name=" + name +
        "}";
    }
}
