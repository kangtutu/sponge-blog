package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.sdo.SpongePermissionDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeSecurityUserDO;
import org.apache.ibatis.annotations.Param;

import java.security.Permission;
import java.util.List;

public interface UserMapper {

    //根据用户名查询指定用户信息
    SpongeSecurityUserDO findSpongeSecurityUserByUserName(@Param("username") String username);

    //查询当前用户拥有的权限
    List<SpongePermissionDO> findPermissionByUserName(@Param("username") String username);


}
