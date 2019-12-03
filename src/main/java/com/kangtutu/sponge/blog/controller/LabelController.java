package com.kangtutu.sponge.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/label")
public class LabelController {

    @GetMapping
    public String type(){
        return "redirect:/blog/label/list";
    }

    @GetMapping("list")
    public String list(){
        return "labelList";
    }

}
