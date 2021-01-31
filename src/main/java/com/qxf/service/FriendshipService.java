package com.qxf.service;

import com.qxf.entity.Friendship;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 好友关系 服务类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public interface FriendshipService extends IService<Friendship> {
    /**
     * 添加好友
     */
    Integer addFriend (Friendship friendship);
    /**
     * 获取好友列表
     */
    List<Friendship> getFriendList(String userId,String friendId);
    /**
     * 获取新朋友列表
     */
    List<Friendship> getNewFriendList(String userId);
    /**
     * 拒绝添加对方为好友
     */
    Integer refuse(Friendship friendship);
    /**
     * 同意添加对方为好友
     */
    Integer agree(Friendship friendship);
}
