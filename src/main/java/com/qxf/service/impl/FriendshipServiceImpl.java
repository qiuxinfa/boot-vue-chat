package com.qxf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qxf.entity.Friendship;
import com.qxf.mapper.FriendshipMapper;
import com.qxf.service.FriendshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 好友关系 服务实现类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@Service
public class FriendshipServiceImpl extends ServiceImpl<FriendshipMapper, Friendship> implements FriendshipService {

    @Resource
    private FriendshipMapper friendshipMapper;

    @Override
    public Integer addFriend(Friendship friendship) {
        friendship.setStatus(0);
        return friendshipMapper.insert(friendship);
    }

    @Override
    public List<Friendship> getFriendList(String userId,String friendId) {
        return friendshipMapper.getFriendList(userId,friendId);
    }

    @Override
    public List<Friendship> getNewFriendList(String userId) {
        return friendshipMapper.getNewFriendList(userId);
    }

    @Override
    public Integer refuse(Friendship friendship) {
        return friendshipMapper.updateFriendStatus(friendship.getFriendId(),friendship.getUserId(),2);
    }

    @Override
    public Integer agree(Friendship friendship) {
        // 先插入一条数据，表示他是我的好友
        friendship.setId(UUID.randomUUID().toString().replaceAll("-",""));
        friendship.setStatus(1);
        friendshipMapper.insert(friendship);
        // 更新好友状态，表示我是他的好友
        return friendshipMapper.updateFriendStatus(friendship.getFriendId(),friendship.getUserId(),1);
    }
}
