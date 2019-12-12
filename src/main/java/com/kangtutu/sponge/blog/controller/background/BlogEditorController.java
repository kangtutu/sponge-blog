package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//后台文章操作
@Controller
@RequestMapping("/editor")
public class BlogEditorController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String index(){
        return "background/blogEditor";
    }

    //新增文章
    @PostMapping("/add")
    @ResponseBody
    public SpongeResultVO add(SpongeBlogDO spongeBlogDO){
        blogService.saveBlog(spongeBlogDO);
        return SpongeResultVO.success();
    }

}
