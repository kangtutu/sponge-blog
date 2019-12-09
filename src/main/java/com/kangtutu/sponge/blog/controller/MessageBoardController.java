package com.kangtutu.sponge.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/board")
public class MessageBoardController {

    //留言板首页
    @GetMapping
    public String board(){
        return "messageBoard";
    }
}
