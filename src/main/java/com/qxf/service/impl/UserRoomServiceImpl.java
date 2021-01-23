package com.qxf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qxf.entity.UserRoom;
import com.qxf.mapper.UserRoomMapper;
import com.qxf.service.UserRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户与群聊 服务实现类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@Service
public class UserRoomServiceImpl extends ServiceImpl<UserRoomMapper, UserRoom> implements UserRoomService {

    @Resource
    private UserRoomMapper roomMapper;

    @Override
    public Integer createRoom(UserRoom room) {
        return roomMapper.insert(room);
    }

    @Override
    public List<UserRoom> getRoomList(String userId) {
        LambdaQueryWrapper<UserRoom> query = Wrappers.lambdaQuery();
        query.eq(UserRoom::getUserId,userId);
        return roomMapper.selectList(query);
    }
}
