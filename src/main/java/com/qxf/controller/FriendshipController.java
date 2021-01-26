package com.qxf.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.qxf.entity.Friendship;
import com.qxf.entity.SysUser;
import com.qxf.service.FriendshipService;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 好友关系 前端控制器
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/friend")
public class FriendshipController {
    @Autowired
    private FriendshipService service;

    // 获取好友列表
    @GetMapping("/list")
    public ResultUtil getFriendList(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultUtil.ok(service.getFriendList(user.getId()));
    }

    // 获取新朋友列表
    @GetMapping("/new/list")
    public ResultUtil getNewFriendList(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultUtil.ok(service.getNewFriendList(user.getId()));
    }

    // 添加新朋友（发送添加请求）
    @PostMapping("/add")
    public ResultUtil addFriend(@RequestBody Friendship friendship){
        friendship.setId(UUID.randomUUID().toString().replaceAll("-",""));
        Integer cnt = service.addFriend(friendship);
        if (cnt > 0){
            return ResultUtil.ok("请求发送成功！");
        }else {
            return ResultUtil.ok("请求发送失败！");
        }

    }

    // 同意对方添加好友的请求
    @PostMapping("/agree")
    public ResultUtil agree(@RequestBody Friendship friendship){
        Integer cnt = service.agree(friendship);
        if (cnt > 0){
            return ResultUtil.ok("操作成功！");
        }else {
            return ResultUtil.ok("操作失败！");
        }

    }

    // 拒绝添加对方为好友
    @PostMapping("/refuse")
    public ResultUtil refuse(@RequestBody Friendship friendship){
        Integer cnt = service.refuse(friendship);
        if (cnt > 0){
            return ResultUtil.ok("操作成功！");
        }else {
            return ResultUtil.ok("操作失败！");
        }

    }

}
