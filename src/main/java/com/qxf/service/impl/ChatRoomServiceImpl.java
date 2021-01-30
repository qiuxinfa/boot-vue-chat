package com.qxf.service.impl;

import com.qxf.entity.ChatRoom;
import com.qxf.mapper.ChatRoomMapper;
import com.qxf.service.ChatRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 群聊 服务实现类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
@Service
public class ChatRoomServiceImpl extends ServiceImpl<ChatRoomMapper, ChatRoom> implements ChatRoomService {
    @Resource
    private ChatRoomMapper roomMapper;

    @Override
    public Integer createRoom(ChatRoom room) {
        return roomMapper.insert(room);
    }

    @Override
    public List<ChatRoom> getRoomList(String userId) {
        return roomMapper.getRoomList(userId);
    }
}
