package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//权限验证
@RestController
@RequestMapping("/sec")
public class SecurityController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public Object getUser(@PathVariable("username")String username){
        return userService.getSpongeUser(username);
    }

    @GetMapping("/parm/{username}")
    public Object getParm(@PathVariable("username")String username){
        return userService.getSpongePermission(username);
    }

}
