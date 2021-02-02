package com.qxf.mapper;

import com.qxf.entity.ChatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
/**
 * <p>
 * 聊天消息 Mapper 接口
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
    @Update("update chat_message set is_read = 1 where to_user_id = #{toUserId}")
    void updateMsgState(@Param("toUserId") String toUserId);
}
