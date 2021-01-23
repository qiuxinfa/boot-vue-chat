package com.qxf.service;

import com.qxf.entity.UserRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户与群聊 服务类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public interface UserRoomService extends IService<UserRoom> {
    /**
     * 创建群聊
     */
    Integer createRoom (UserRoom room);

    /**
     * 获取群聊列表
     */
    List<UserRoom> getRoomList(String userId);
}
