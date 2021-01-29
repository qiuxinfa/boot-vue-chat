package com.qxf.mapper;

import com.qxf.entity.Friendship;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 好友关系 Mapper 接口
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public interface FriendshipMapper extends BaseMapper<Friendship> {
    @Select("select f.friend_id,f.remark,u.avatar,u.is_online from friendship f,sys_user u where f.`status` = 1 and f.user_id = u.id and u.id=#{userId} order by f.remark")
    List<Friendship> getFriendList(@Param("userId") String userId);

    // 查询新朋友列表
    @Select("select u.id friendId,u.username remark,u.avatar from friendship f,sys_user u where f.`status` = 0 and f.user_id = u.id and f.friend_id=#{userId} order by u.username")
    List<Friendship> getNewFriendList(@Param("userId") String userId);

    // 修改好友状态
    @Update("update friendship set `status` = #{status} where user_id = #{userId} and friend_id = #{friendId}")
    Integer updateFriendStatus(@Param("userId") String userId,@Param("friendId") String friendId,@Param("status") Integer status);
}
