package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//后台文章操作
@Controller
@RequestMapping("/bg/blog")
public class BackgroundBlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String index(Model model){
        //获取全量博客列表数据
        ResultObjectDTO result = blogService.getBlogByTerm(null);
        if(result.getCode() != 200){
            model.addAttribute("code",500);
            model.addAttribute("message","查询文章列表数据失败");
            return "error";
        }
        model.addAttribute("blogs",result.getData());
        return "background/blog";
    }

    //新增文章
    @PostMapping("/add")
    @ResponseBody
    public SpongeResultVO add(SpongeBlogDO spongeBlogDO){
        blogService.saveBlog(spongeBlogDO);
        return SpongeResultVO.success();
    }

}
