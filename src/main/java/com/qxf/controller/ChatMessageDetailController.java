package com.qxf.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qxf.dto.ChatRecordDto;
import com.qxf.entity.SysUser;
import com.qxf.service.ChatMessageDetailService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 聊天消息详情 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/message/detail")
public class ChatMessageDetailController {

    @Autowired
    private ChatMessageDetailService detailService;

    // 离线消息
    @GetMapping("/offline")
    public ResultUtil getUserMsg(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultUtil.ok(detailService.getOfflineMsg(null,user.getId(),null));
    }

    // 获取聊天记录
    @GetMapping("/getChatRecord")
    public ResultUtil getChatRecord(ChatRecordDto dto){
        Integer msgType = dto.getMsgType();
        if (msgType != 1 && msgType != 2){
            return ResultUtil.error("参数传递错误，消息类型只能是1或2");
        }
        if (msgType == 1){
            // 群聊
            if (StringUtils.isEmpty(dto.getRoomId())){
                return ResultUtil.error("参数传递错误，聊天室id不能为空");
            }else {
                return ResultUtil.ok(detailService.getRoomChatRecord(dto));
            }

        }else {
            // 私聊
            if (StringUtils.isEmpty(dto.getToUserId()) || StringUtils.isEmpty(dto.getFromUserId())){
                return ResultUtil.error("参数传递错误，消息的发送者或接收者不能为空");
            }else {
                return ResultUtil.ok(detailService.getUserChatRecord(dto));
            }

        }
    }

}
