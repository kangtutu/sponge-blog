package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLimitResultVO;
import com.kangtutu.sponge.blog.pojo.SKTerm;
import com.kangtutu.sponge.blog.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
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

    @GetMapping
    public String archiving(){
        return "redirect:/blog/archiving/list";
    }

    //页面数据初始化
    @GetMapping("/list")
    public String list(Model model){
        //创建条件查询对象，封装查询参数
        SKTerm skTerm = new SKTerm();
        skTerm.setPageSize(pageSize);
        skTerm.setTopCurrPage(0);
        SKLimitResultVO skLimitResultVO = setResult(skTerm, 1);
        //获取表中存在的年份数据
        List<Integer> year = blogService.getBlogPublishYear();
        model.addAttribute("limitResult",skLimitResultVO);
        model.addAttribute("years",year);
        System.out.println(skLimitResultVO);
        return "articleArchiving";
    }

    /**
     * 异步分页查询
     * @param currPage 需要查询的页码数
     * @param model
     * @return
     */
    @GetMapping("/page/{currPage}")
    @ResponseBody
    public SKLimitResultVO getLimitBlog(@PathVariable("currPage") Integer currPage,Model model){
        //创建条件查询对象，封装查询参数
        SKTerm skTerm = new SKTerm();
        skTerm.setPageSize(pageSize);
        int cPage = (currPage-1)*pageSize;
        skTerm.setTopCurrPage(cPage);
        SKLimitResultVO skLimitResultVO = setResult(skTerm, currPage);
        return skLimitResultVO;
    }

    //分页查询
    @GetMapping("/{year}/{month}/{topCurrPage}")
    @ResponseBody
    public SKLimitResultVO archivingAll(@PathVariable("year") Integer year,
                               @PathVariable("month") Integer month,
                               @PathVariable("topCurrPage") Integer topCurrPage){
        log.info("按年份查询全量博客数据，传入年份参数为:{}，传入月份:{}，传入页码数:{}",year,month,topCurrPage);
        SKTerm skTerm = new SKTerm();
        skTerm.setPublishYear(year);
        skTerm.setPublishMonth(month);
        skTerm.setPageSize(pageSize);
        int size = (topCurrPage-1)*pageSize;
        skTerm.setTopCurrPage(size);
        return setResult(skTerm,topCurrPage);
    }

    /**
     * 封装分页查询数据
     * @param topCurrPage 当前页数
     * @return
     */
    public SKLimitResultVO setResult(SKTerm skTerm,Integer topCurrPage){
        // 查询全量博客数据，按照年份及月份进行排序
        List<SKBlog> blogs = blogService.getBlogByYearAndMonth(skTerm);
        //查询总条数
        Integer total = blogService.getBlogByTermAndTotal(skTerm);
        //创建分页数据对象
        SKLimitResultVO slrv = new SKLimitResultVO();
        slrv.setPageIndex(topCurrPage);//当前页码数
        int pageTotal = new BigDecimal(total).divide(new BigDecimal(pageSize),0,BigDecimal.ROUND_UP).intValue();
        if(topCurrPage>=pageTotal){
            slrv.setLastPage(false);
        }
        slrv.setTotalNumber(pageTotal);//总页码数
        slrv.setPageSize(pageSize);//每页显示条数
        slrv.setData(blogs);
        return slrv;
    }

}
