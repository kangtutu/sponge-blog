package com.kangtutu.sponge.blog.controller.background;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//后台文章操作
@Controller
@RequestMapping("/bg/user")
public class BackgroundUserController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundUserController.class);

   @GetMapping
    public String userIndex(){
       log.info("[后台分类]");
       //查询所有分类
       return "background/user";
   }

}
