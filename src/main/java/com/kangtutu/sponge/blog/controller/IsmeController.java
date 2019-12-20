package com.kangtutu.sponge.blog.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/isme")
@Slf4j
@Api(tags = "[前台] 站长信息相关接口")
public class IsmeController {

    //关于我首页
    @GetMapping
    public String isme(){
        return "isme";
    }

}
