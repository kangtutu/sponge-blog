package com.kangtutu.sponge.blog.service;

import com.kangtutu.sponge.blog.pojo.sdo.SpongePermissionDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeSecurityUserDO;

import java.util.List;

public interface UserService {

    //获取指定用户
    SpongeSecurityUserDO getSpongeUser(String username);

    List<SpongePermissionDO> getSpongePermission(String username);
}
