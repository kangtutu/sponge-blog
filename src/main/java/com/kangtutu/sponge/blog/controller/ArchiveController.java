package com.kangtutu.sponge.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/archive")
public class ArchiveController {

    //归档首页
    @GetMapping
    public String archive(){
        return "archive";
    }
}
