package com.kangtutu.sponge.blog.config.security;

import com.kangtutu.sponge.blog.pojo.sdo.SpongePermissionDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeSecurityUserDO;
import com.kangtutu.sponge.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户身份校验及权限封装
 */
@Configuration
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //用户验证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        Collection<GrantedAuthority> authority = null;
        //从数据库中查出对应的用户数据
        SpongeSecurityUserDO spongeUser = userService.getSpongeUser(username);
        if(spongeUser != null){
            //查询对应用户的权限信息
            List<SpongePermissionDO> spongePermission = userService.getSpongePermission(username);
            //进行权限封装
            if(spongePermission != null && spongePermission.size() >0){
                authority = getAuthority(spongePermission);
            }
            //用户密码必须经过security的加密处理
            user = new User(username,passwordEncoder.encode(spongeUser.getPassword()),authority);
        }
        return user;
    }


    //用户权限的封装
    private Collection<GrantedAuthority> getAuthority(List<SpongePermissionDO> spongePermission){
        List<GrantedAuthority> list = new ArrayList<>();
        for(SpongePermissionDO Permission : spongePermission){
            list.add(new SimpleGrantedAuthority(Permission.getParmTarg()));
        }
        return list;
    }

}
