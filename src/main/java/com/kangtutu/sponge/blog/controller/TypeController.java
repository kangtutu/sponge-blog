package com.kangtutu.sponge.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/type")
public class TypeController {

    @GetMapping
    public String type(){
        return "redirect:/blog/type/list";
    }

    @GetMapping("list")
    public String list(){
        return "typeList";
    }

}
