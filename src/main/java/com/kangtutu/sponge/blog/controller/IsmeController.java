package com.kangtutu.sponge.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/isme")
public class IsmeController {

    //关于我首页
    @GetMapping
    public String isme(){
        return "isme";
    }

}
