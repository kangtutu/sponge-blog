package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import com.kangtutu.sponge.blog.service.CommentService;
import com.kangtutu.sponge.blog.service.LabelService;
import com.kangtutu.sponge.blog.service.TypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//后台文章操作
@Controller
@RequestMapping("/bg/blog")
@Api(tags = "[管理后台] 文章管理相关接口")
public class BackgroundBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    /**
     * 博客列表首页
     * @param model
     * @return
     */
    @GetMapping
    public String index(Model model) {
        //获取全量博客列表数据
        ResultObjectDTO result = blogService.getBlogByTerm(null);
//        if(result.getCode() != 200){
//            model.addAttribute("code",500);
//            model.addAttribute("message","查询文章列表数据失败");
//            return "error";
//        }
        model.addAttribute("blogs", result.getData());
        return "background/blog";
    }

    @GetMapping("/toAdd")
    public String toAdd(Model model){
        //查询分类及标签
        ResultObjectDTO labelAll = labelService.getLabelAll(true);
        ResultObjectDTO typeAll = typeService.getTypeAll(true);

        model.addAttribute("labels", labelAll.getData());
        model.addAttribute("types", typeAll.getData());
        return "background/blogAdd";
    }

    /**
     * 新增文章
     * @param spongeBlogDO
     * @return
     */
    @PostMapping("/add")
    public String add(SpongeBlogDO spongeBlogDO) {
        ResultObjectDTO result = blogService.saveBlog(spongeBlogDO);
        if (result.getCode() != 200) {
            return "error";
        }
        return "redirect:/bg/blog";
    }

    /**
     * 查询博客数据进到博客信息修改页面
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/toUpdate/{blogId}")
    public String toUpdate(@PathVariable("blogId") Integer blogId, Model model) {
        ResultObjectDTO blog = blogService.getBlogById(blogId);
//        if (blog.getCode() != 200) {
//            model.addAttribute("code", 500);
//            model.addAttribute("message", "未查询到指定博客数据");
//            return "error";
//        }
        //查询分类及标签
        ResultObjectDTO labelAll = labelService.getLabelAll(true);
        ResultObjectDTO typeAll = typeService.getTypeAll(true);

        model.addAttribute("labels", labelAll.getData());
        model.addAttribute("types", typeAll.getData());
        model.addAttribute("blog", blog.getData());
        return "background/blogEditor";
    }

    /**
     * 执行更新操作
     * @param spongeBlogDO
     * @return
     */
    @PostMapping("/update")
    public String updateBlog(SpongeBlogDO spongeBlogDO){
        ResultObjectDTO result = blogService.updateBlog(spongeBlogDO);
        if(result.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/blog";
    }

    /**
     * 删除指定博客id数据及相关评论数据
     * @param blogId
     * @return
     */
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
