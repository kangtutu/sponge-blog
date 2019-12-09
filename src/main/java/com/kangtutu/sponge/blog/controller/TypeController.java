package com.kangtutu.sponge.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/type")
public class TypeController {

    //分类首页
    @GetMapping
    public String type(){
        return "typeList";
    }

}
