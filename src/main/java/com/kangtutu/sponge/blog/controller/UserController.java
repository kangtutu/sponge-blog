package com.kangtutu.sponge.blog.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/user")
@Slf4j
@Api(tags = "[前台] 用户相关接口")
public class UserController {

    @GetMapping
    public String userIndex(){
        return "background/user";
    }

}
