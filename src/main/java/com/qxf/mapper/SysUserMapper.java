package com.qxf.mapper;

import com.qxf.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
     * 修改个人信息
     */
    Integer updateUser(SysUser user);

    /**
     * 查询用户列表
     */
    List<SysUser> queryUserList(@Param("userId") String userId,@Param("username") String username, @Param("isFriend") Integer isFriend);
}
