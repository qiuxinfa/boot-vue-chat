package com.qxf.mapper;

import com.qxf.dto.ChatRecordDto;
import com.qxf.entity.ChatMessageDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 聊天消息详情 Mapper 接口
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public interface ChatMessageDetailMapper extends BaseMapper<ChatMessageDetail> {
    // 获取离线消息，fromUserId和roomId，只传一个，另一个传空值即可，分别实现单聊和群聊离线消息的拉取
    List<ChatMessageDetail> getOfflineMsg(@Param("fromUserId") String fromUserId,@Param("toUserId") String toUserId,@Param("roomId") String roomId);
    // 获取私聊的聊天记录
    List<ChatMessageDetail> getUserChatRecord(@Param("dto") ChatRecordDto dto);
    // 获取群聊的聊天记录
    List<ChatMessageDetail> getRoomChatRecord(@Param("dto") ChatRecordDto dto);
}
