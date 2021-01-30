package com.qxf.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qxf.entity.ChatRoom;
import com.qxf.entity.SysUser;
import com.qxf.entity.UserRoom;
import com.qxf.service.ChatRoomService;
import com.qxf.service.UserRoomService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 群聊 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/room")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserRoomService userRoomService;
    @GetMapping("/list")
    public ResultUtil getRoomList(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultUtil.ok(chatRoomService.getRoomList(user.getId()));
    }

    @PostMapping("/add")
    public ResultUtil addFriend(@RequestBody ChatRoom chatRoom){
        String userIds = chatRoom.getUserIds();
        if (StringUtils.isEmpty(userIds)){
            return ResultUtil.error("请选择群聊用户！");
        }
        String[] ids = userIds.split(",");
        String roomId = UUID.randomUUID().toString().replaceAll("-","");
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 设置群主，默认为创建人
        chatRoom.setMasterId(user.getId());
        chatRoom.setId(roomId);
        chatRoom.setCreateTime(new Date());
        chatRoomService.save(chatRoom);
        // 建立用户-群聊的关联关系
        for (String userId : ids){
            UserRoom userRoom = new UserRoom();
            userRoom.setId(UUID.randomUUID().toString().replaceAll("-",""));
            userRoom.setUserId(userId);
            userRoom.setRoomId(roomId);
            userRoomService.save(userRoom);
        }
        return ResultUtil.ok("创建成功！");
    }
}
