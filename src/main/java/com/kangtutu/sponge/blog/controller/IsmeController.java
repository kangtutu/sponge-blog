package com.kangtutu.sponge.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
@Controller
@RequestMapping("/blog/isme")
public class IsmeController {

    @GetMapping
    public String type(){
        return "redirect:/blog/isme/index";
    }

    @GetMapping("/index")
    public String list(){
        return "isme";
    }

}

