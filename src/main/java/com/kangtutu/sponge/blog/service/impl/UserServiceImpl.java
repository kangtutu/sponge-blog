package com.kangtutu.sponge.blog.service.impl;

import com.kangtutu.sponge.blog.mapper.UserMapper;
import com.kangtutu.sponge.blog.pojo.sdo.SpongePermissionDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeSecurityUserDO;
import com.kangtutu.sponge.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SpongeSecurityUserDO getSpongeUser(String username) {
        return userMapper.findSpongeSecurityUserByUserName(username);
    }

    @Override
    public List<SpongePermissionDO> getSpongePermission(String username) {
        return userMapper.findPermissionByUserName(username);
    }
}
