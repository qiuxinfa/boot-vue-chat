package com.qxf.mapper;

import com.qxf.entity.Friendship;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Select("select f.friend_id,f.remark,u.avatar from friendship f,sys_user u where f.`status` = 1 and f.user_id = u.id and u.id=#{userId}")
    List<Friendship> getFriendList(@Param("userId") String userId);
}
