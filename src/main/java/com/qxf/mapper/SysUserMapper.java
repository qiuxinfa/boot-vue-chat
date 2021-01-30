package com.qxf.mapper;

import com.qxf.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     *  根据群聊id，查询群聊所有用户的在线状态
     */
    List<SysUser> getUserStateByRoomId(@Param("roomId") String roomId);

    /**
     * 修改用户状态
     */
    @Update("update sys_user set is_online = #{isOnline} where id = #{userId}")
    Integer updateUserState(@Param("userId") String userId,@Param("isOnline") Integer isOnline);
    /**
     * 修改个人信息
     */
    Integer updateUser(SysUser user);

    /**
     * 查询用户列表
     */
    List<SysUser> queryUserList(@Param("userId") String userId,@Param("username") String username, @Param("isFriend") Integer isFriend);
}
