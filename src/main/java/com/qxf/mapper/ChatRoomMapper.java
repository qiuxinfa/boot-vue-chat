package com.qxf.mapper;

import com.qxf.entity.ChatRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 群聊 Mapper 接口
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public interface ChatRoomMapper extends BaseMapper<ChatRoom> {
    List<ChatRoom> getRoomList(@Param("userId") String userId);
}
