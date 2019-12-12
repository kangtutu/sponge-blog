package com.kangtutu.sponge.blog.controller.background;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//后台文章操作
@Controller
@RequestMapping("/blog/background/comment")
public class BackgroundCommentController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundCommentController.class);

   @GetMapping
    public String comment(){
       log.info("[后台评论留言]");
       //查询所有标签
       return "";
   }

}
