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
    public List<Friendship> getFriendList(String userId) {
        return friendshipMapper.getFriendList(userId);
    }
}
