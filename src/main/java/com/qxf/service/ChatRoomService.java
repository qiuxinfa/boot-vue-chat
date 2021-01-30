package com.qxf.service;

import com.qxf.entity.ChatRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 群聊 服务类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public interface ChatRoomService extends IService<ChatRoom> {
    /**
     * 创建群聊
     */
    Integer createRoom (ChatRoom room);

    /**
     * 获取群聊列表
     */
    List<ChatRoom> getRoomList(String userId);
}
