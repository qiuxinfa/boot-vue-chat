package com.qxf.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qxf.entity.SysUser;
import com.qxf.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/30 9:52
 **/
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;

    @Autowired
    private SysUserService userService;

    // 缓存是否在线，1在线 0离线
    public void setOnline(String userId,String isOnline){
        stringRedisTemplate.opsForValue().set(userId,isOnline);
    }

    // 判断是否在线
    public String isOnline(String userId){
        String s = stringRedisTemplate.opsForValue().get(userId);
        if (StringUtils.isEmpty(s)){
            // 查数据库
            SysUser user = userService.getById(userId);
            if (user != null){
                s = ""+user.getIsOnline();
            }
        }
        return s;
    }

    // 缓存群聊所有用户的在线状态
    public void setRoomUserState(String roomId, List<SysUser> userList){
        redisTemplate.opsForValue().set(roomId,userList);
    }

    // 根据群聊id返回用户在线状态
    public List<SysUser> getRoomUserState(String roomId){
        Object o = redisTemplate.opsForValue().get(roomId);
        if (o == null){
            List<SysUser> userList = userService.getUserStateByRoomId(roomId);
            this.setRoomUserState(roomId,userList);
            return userList;
        }else {
            return (List<SysUser>) o;
        }
    }
}
