package com.qxf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qxf.entity.Friendship;
import com.qxf.entity.SysUser;
import com.qxf.mapper.FriendshipMapper;
import com.qxf.mapper.SysUserMapper;
import com.qxf.mapper.UserRoomMapper;
import com.qxf.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService,SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> query = Wrappers.lambdaQuery();
        query.eq(SysUser::getUsername,username);
        return sysUserMapper.selectOne(query);
    }

    @Override
    public Integer checkUsername(String username) {
        LambdaQueryWrapper<SysUser> query = Wrappers.lambdaQuery();
        query.eq(SysUser::getUsername,username);
        return sysUserMapper.selectCount(query);
    }

    @Override
    public Integer register(SysUser user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(1);
        return sysUserMapper.insert(user);
    }

    @Override
    public List<SysUser> queryUserList(String username) {
        LambdaQueryWrapper<SysUser> query = Wrappers.lambdaQuery();
        if (StringUtils.isNotEmpty(username)){
            query.like(SysUser::getUsername,username);
        }
        return sysUserMapper.selectList(query);
    }
}
