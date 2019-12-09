package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.pojo.vo.ResultCodeEnumVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String index(){
        return "redirect:/blog/index";
    }

    @GetMapping("/index")
    public Object list(Model model) {
        log.info("进入Sponge首页");
        //查询页面需要展示的博客文章
        SpongeTermDO term = SpongeTermDO.getInstance();
        term.setStartPage(0);
        term.setPageSize(pageSize);
        ResultObjectDTO resultObjectDTO = blogService.getBlogByReadingQuantity(term);
        log.info("查询到的首页文章列表数据,{}",resultObjectDTO);
        if(!resultObjectDTO.getStatus()){
            log.info("查询首页文章列表数据失败");
            model.addAttribute("code",ResultCodeEnumVO.FAILURE.getCode());
            model.addAttribute("message","查询首页列表数据失败");
            return "error";
        }
        model.addAttribute("blogs",resultObjectDTO.getData());
        return "index";
    }

    @GetMapping("/get/{blogId}")
    public Object getBlogById(@PathVariable("blogId")Integer blogId,Model model){
        ResultObjectDTO resultObjectDTO = blogService.getBlogById(blogId);
        if(!resultObjectDTO.getStatus()){
            log.info("查询文章ID:"+blogId+"数据失败");
            model.addAttribute("code",ResultCodeEnumVO.FAILURE.getCode());
            model.addAttribute("message","查询文章ID:"+blogId+"数据失败");
            return "error";
        }
        log.info("查询文章ID:"+blogId+"数据成功,{}",resultObjectDTO.getData());
        model.addAttribute("blog",resultObjectDTO.getData());
        return "blog";
    }

}
