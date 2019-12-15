package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import com.kangtutu.sponge.blog.service.CommentService;
import com.kangtutu.sponge.blog.service.LabelService;
import com.kangtutu.sponge.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//后台文章操作
@Controller
@RequestMapping("/bg/blog")
public class BackgroundBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String index(Model model) {
        //获取全量博客列表数据
        ResultObjectDTO result = blogService.getBlogByTerm(null);
        //查询分类及标签
        ResultObjectDTO labelAll = labelService.getLabelAll(true);
        ResultObjectDTO typeAll = typeService.getTypeAll(true);

//        if(result.getCode() != 200){
//            model.addAttribute("code",500);
//            model.addAttribute("message","查询文章列表数据失败");
//            return "error";
//        }

        model.addAttribute("labels", labelAll.getData());
        model.addAttribute("types", typeAll.getData());
        model.addAttribute("blogs", result.getData());
        return "background/blog";
    }

    //新增文章
    @PostMapping("/add")
    public String add(SpongeBlogDO spongeBlogDO) {
        ResultObjectDTO result = blogService.saveBlog(spongeBlogDO);
        if (result.getCode() != 200) {
            return "error";
        }
        return "redirect:/bg/blog";
    }

    @GetMapping("/toUpdate/{blogId}")
    public String toUpdate(@PathVariable("blogId") Integer blogId, Model model) {
        ResultObjectDTO blog = blogService.getBlogById(blogId);
        if (blog.getCode() != 200) {
            model.addAttribute("code", 500);
            model.addAttribute("message", "未查询到指定博客数据");
            return "error";
        }
        model.addAttribute("blog", blog);
        return "background/blogEditor";
    }

    @GetMapping("/delete/{blogId}")
    public String toUpdate(@PathVariable("blogId") Integer blogId) {
        //删除对应id的博客文章
        ResultObjectDTO resultBlog = blogService.deleteBlogById(blogId);
        //删除对应id的所有评论信息
        ResultObjectDTO resultComment = commentService.deleteCommentByBlogId(blogId);
        if (resultBlog.getCode() != 200 || resultComment.getCode() != 200) {
            return "error";
        }
        return "redirect:/bg/blog";
    }

}
