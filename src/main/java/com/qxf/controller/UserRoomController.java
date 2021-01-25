package com.qxf.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qxf.entity.SysUser;
import com.qxf.entity.UserRoom;
import com.qxf.service.UserRoomService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户与群聊 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/room")
public class UserRoomController {
    @Autowired
    private UserRoomService service;

    @GetMapping("/list")
    public ResultUtil getRoomList(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultUtil.ok(service.getRoomList(user.getId()));
    }

    @PostMapping("/add")
    public ResultUtil addFriend(@RequestBody UserRoom userRoom){
        String userIds = userRoom.getUserIds();
        if (StringUtils.isEmpty(userIds)){
            return ResultUtil.error("请选择群聊用户！");
        }
        String[] ids = userIds.split(",");
        String roomId = UUID.randomUUID().toString().replaceAll("-","");
        String roomName = userRoom.getRoomName();
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (String userId : ids){
            UserRoom room = new UserRoom();
            room.setId(UUID.randomUUID().toString().replaceAll("-",""));
            room.setUserId(userId);
            room.setRoomId(roomId);
            room.setRoomName(roomName);
            if (userId.equals(user.getId())){
                room.setUserRole(1);  // 群主
            }else {
                room.setUserRole(3);  // 普通成员
            }

            service.createRoom(room);
        }
        return ResultUtil.ok("创建成功！");
    }
}
