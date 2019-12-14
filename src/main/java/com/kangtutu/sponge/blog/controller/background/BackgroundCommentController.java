package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//后台文章操作
@Controller
@RequestMapping("/bg/comment")
public class BackgroundCommentController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundCommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String comment(Model model){
       log.info("[后台评论留言]");
       //查询所有标签
        ResultObjectDTO result = commentService.getCommentByTerm(null);
        if(result.getCode() != 200){
            model.addAttribute("code",500);
            model.addAttribute("message","查询评论信息失败");
            return "error";
        }
        model.addAttribute("comments",result.getData());
        return "background/comment";
    }

}
