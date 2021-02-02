package com.qxf.service.impl;

import com.qxf.entity.ChatMessage;
import com.qxf.mapper.ChatMessageMapper;
import com.qxf.service.ChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 聊天消息 服务实现类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {

    @Resource
    private ChatMessageMapper messageMapper;

    @Override
    public void updateMsgState(String toUserId) {
        messageMapper.updateMsgState(toUserId);
    }
}
