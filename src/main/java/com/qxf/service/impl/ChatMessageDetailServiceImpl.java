package com.qxf.service.impl;

import com.qxf.dto.ChatRecordDto;
import com.qxf.entity.ChatMessageDetail;
import com.qxf.mapper.ChatMessageDetailMapper;
import com.qxf.service.ChatMessageDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 聊天消息详情 服务实现类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
@Service
public class ChatMessageDetailServiceImpl extends ServiceImpl<ChatMessageDetailMapper, ChatMessageDetail> implements ChatMessageDetailService {

    @Resource
    private ChatMessageDetailMapper detailMapper;

    @Override
    public List<ChatMessageDetail> getOfflineMsg(String fromUserId, String toUserId, String roomId) {
        return detailMapper.getOfflineMsg(fromUserId,toUserId,roomId);
    }

    @Override
    public List<ChatMessageDetail> getUserChatRecord(ChatRecordDto dto) {
        return detailMapper.getUserChatRecord(dto);
    }

    @Override
    public List<ChatMessageDetail> getRoomChatRecord(ChatRecordDto dto) {
        return detailMapper.getRoomChatRecord(dto);
    }

}
