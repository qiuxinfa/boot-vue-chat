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

    @GetMapping("/list")
    public ResultUtil getFriendList(){
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultUtil.ok(service.getFriendList(user.getId()));
    }

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

}
