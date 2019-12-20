package com.kangtutu.sponge.blog.controller.background;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//后台文章操作
@Controller
@RequestMapping("/bg/user")
@Slf4j
@Api(tags = "[管理后台] 用户信息管理相关接口")
public class BackgroundUserController {

   @GetMapping
    public String userIndex(){
       log.info("[后台分类]");
       //查询所有分类
       return "background/user";
   }

}
