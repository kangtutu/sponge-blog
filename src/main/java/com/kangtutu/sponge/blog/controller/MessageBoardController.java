package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.CommentService;
import com.kangtutu.sponge.blog.pojo.SKComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

//评论信息相关
@Controller
@RequestMapping("/blog/message")
public class MessageBoardController {

    private static final Logger log = LoggerFactory.getLogger(MessageBoardController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String message(){
        return "redirect:/blog/message/index";
    }

    @GetMapping("/index")
    public String index(){
        return "messageBoard";
    }



    //异步调用直接返回结果，页面进行自动刷新
    @GetMapping("/add")
    @ResponseBody
    public String addComment(SKComment skComment){
        log.info("进入添加评论信息逻辑");
        commentService.saveComment(skComment);
        return "OK";
    }

    //通过博客id查询对应的评论数据并进行封装
    @GetMapping("/{blogId}")
    @ResponseBody
    public Object Storm(@PathVariable("blogId")Integer blogId){
        List<Map<String, Object>> comments = commentService.getCommentByBlogId(blogId);
        return comments;
    }

    //添加评论测试数据
    public void addComments(){
        for(int i=0;i<20;i++){
            SKComment comment = new SKComment();
            comment.setBlogId(10);
            comment.setTitle("博客文章标题");
            comment.setObServer("kangtutu");
            comment.setHeadAddress("www.baidu.com");
            comment.setContent("测试博客评论数据+i");
            commentService.saveComment(comment);
        }
    }
}
