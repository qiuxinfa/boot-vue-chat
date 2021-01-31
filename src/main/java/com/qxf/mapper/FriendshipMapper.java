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
    // 查询好友列表
    List<Friendship> getFriendList(@Param("userId") String userId,@Param("friendId") String friendId);

    // 查询新朋友列表
    List<Friendship> getNewFriendList(@Param("userId") String userId);

    // 修改好友状态
    @Update("update friendship set `status` = #{status} where user_id = #{userId} and friend_id = #{friendId}")
    Integer updateFriendStatus(@Param("userId") String userId,@Param("friendId") String friendId,@Param("status") Integer status);
}
