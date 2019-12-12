package com.kangtutu.sponge.blog.controller.background;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理后台首页
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
@Controller
@RequestMapping("/blog/background")
public class BackgroundIndexController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundIndexController.class);

    @GetMapping
    public String index(){
        log.info("[后台管理首页] 方法内");
        //查询文章总条数
        //查询评论总条数
        //查询需要回复的评论条数
        //查询留言条数
        return "";
    }






}

