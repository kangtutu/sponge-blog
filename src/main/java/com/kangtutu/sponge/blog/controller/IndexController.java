package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.pojo.vo.ResultCodeEnumVO;
import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
@Slf4j
@Api(tags = "[前台] 博客首页相关接口")
public class IndexController {

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    @ApiOperation(value = "博客首页重定向到index",notes = "博客首页重定向到index")//接口说明
    public String index(){
        return "redirect:/blog/index";
    }

    @GetMapping("/index")
    @ApiOperation(value = "获取首页数据",notes = "获取首页数据")
    public String indexData(Model model) {
        log.info("[博客首页] 进入博客首页方法内");
        //查询页面需要展示的博客文章
        SpongeTermDO term = SpongeTermDO.getInstance();
        term.setStartPage(0);
        term.setPageSize(pageSize);
        ResultObjectDTO resultObjectDTO = blogService.getBlogByReadingQuantity(term);
        log.info("[博客首页] 查询到的博客列表数据,{}",resultObjectDTO);
        if(!resultObjectDTO.getStatus()){
            log.info("[博客首页] 查询博客列表数据失败");
            model.addAttribute("code", ResultCodeEnumVO.FAILURE.getCode());
            model.addAttribute("message","查询首页列表数据失败");
            return "error";
        }
        model.addAttribute("blogs",resultObjectDTO.getData());
        return "index";
    }
}
