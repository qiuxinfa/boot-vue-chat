package com.qxf.service;

import com.qxf.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public interface SysUserService extends IService<SysUser> {
    /**
     *  根据群聊id，查询群聊所有用户的在线状态
     */
    List<SysUser> getUserStateByRoomId(String roomId);
    /**
     * 用户上线
     */
    Integer userOnline(String userId);
    /**
     * 用户下线
     */
    Integer userOffline(String userId);
    /**
     * 修改个人信息
     */
    Integer updateUser(SysUser user);
    
    /**
     * 检查用户名是否存在，用于用户注册时校验
     */
    Integer checkUsername(String username);

    /**
     * 检查用户名是否存在，用于用户注册时校验
     */
    Integer register(SysUser user);

    /**
     * 查询用户列表
     */
    List<SysUser> queryUserList(String username,Integer isFriend);
}
