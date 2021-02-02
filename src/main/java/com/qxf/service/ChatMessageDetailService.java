package com.qxf.service;

import com.qxf.entity.ChatMessageDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 聊天消息详情 服务类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
public interface ChatMessageDetailService extends IService<ChatMessageDetail> {
    // 获取离线消息，fromUserId和roomId，只传一个，另一个传空值即可，分别实现单聊和群聊离线消息的拉取
    List<ChatMessageDetail> getOfflineMsg(String fromUserId,String toUserId,String roomId);
}
