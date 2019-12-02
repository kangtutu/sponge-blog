package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLimitResultVO;
import com.kangtutu.sponge.blog.pojo.SKTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//文章归档控制器类
@Controller
@RequestMapping("/blog/archiving")
public class ArticleArchivingController {

    private static final Logger log = LoggerFactory.getLogger(ArticleArchivingController.class);

    @Autowired
    private BlogService blogService;

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

    /**
     * 按照年份查询指定博客数据
     * @return
     */
    @GetMapping("/{year}/{topCurrPage}")
    @ResponseBody
    public Object archivingByYear(@PathVariable("year") Integer year,
                                  @PathVariable("topCurrPage") Integer topCurrPage){
        log.info("按年份查询全量博客数据，传入年份参数为:{}，查询的页数",year,topCurrPage);
        SKTerm skTerm = new SKTerm();
        skTerm.setPublishYear(year);
        skTerm.setPageSize(pageSize);
        int size = (topCurrPage-1)*pageSize;
        skTerm.setTopCurrPage(size);
        List<SKBlog> blogs = blogService.getBlogByTerm(skTerm);

        //查询对应条件的总条数
        Integer total = blogService.getBlogByTermAndTotal(skTerm);

        SKLimitResultVO skLimitResultVO = setResult(blogs, topCurrPage, total);
        return skLimitResultVO;
    }

    //分页查询
    @GetMapping("/{year}/{month}/{topCurrPage}")
    @ResponseBody
    public Object archivingAll(@PathVariable("year") Integer year,
                               @PathVariable("month") Integer month,
                               @PathVariable("topCurrPage") Integer topCurrPage){
        log.info("按年份查询全量博客数据，传入年份参数为:{}",year);
        SKTerm skTerm = new SKTerm();
        skTerm.setPublishYear(year);
        skTerm.setPublishMonth(month);
        skTerm.setPageSize(pageSize);
        int size = (topCurrPage-1)*pageSize;
        skTerm.setTopCurrPage(size);
        List<SKBlog> blogs = blogService.getBlogByTerm(skTerm);
        return blogs;
    }

    //封装查询及返回结果的方法
    public SKLimitResultVO setResult(List<SKBlog> blogs,Integer topCurrPage,Integer total){
        SKLimitResultVO slrv = new SKLimitResultVO();
        slrv.setPageIndex(topCurrPage);
        slrv.setTotalNumber(total);
        slrv.setPageSize(pageSize);
        slrv.setData(blogs);
        return slrv;
    }

}
