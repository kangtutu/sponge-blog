package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.Service.CommentService;
import com.kangtutu.sponge.blog.exception.custom.ParameterCalibrationException;
import com.kangtutu.sponge.blog.mapper.CommentMapper;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLimitResultVO;
import com.kangtutu.sponge.blog.pojo.SKTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/blog")
public class BlogController {

    //分页查询每页显示条数
    private static final Integer PAGE_SIZE = 10;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String index(){
        return "redirect:/blog/index";
    }

    /**
     * 博客首页
     * 1. 获取首页推荐博客文章，此处按照发布时间进行降序排序取前5篇
     * 2. 获取热门博客文章，此处按照浏览量进行降序排序取前5篇
     * 3. 获取首页博客列表数据，按照发布时间降序排序进行分页查找展示
     * 3.1 页面每次显示15篇，可以分页查询两次，然后页面显示查看更多按钮，跳转到博客文章列表页
     * 4. 页面中的右侧联系方式部分也可以通过数据库进行存储，实时的增删改查(后续补充)
     * 5. 按照发布年份查询出有哪些年份的文章，然后通过右侧进行年份和月份的展示
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String indexs(Model model) {
        //查询总条数
        Integer total = blogService.getBlogTotal();
        //创建条件对象
        SKTerm skTerm = new SKTerm();
        skTerm.setStatus(true);//页面中展示的博客文章状态必须是启用的否则不予显示
        //需要将分页数据进行封装
        SKLimitResultVO blogLimitList = setSKLimitResultVO(skTerm, total,PAGE_SIZE, 1);
        //热门文章-首页轮播图部分提供跳转入口，查询数据只要阅读量排序的前5条
        List<SKBlog> hotBlog = blogService.getHotBlogByReadingQuantity(5,1);
        //首页推荐相关博客
        skTerm.setOpenHomeRecommend(true);
        List<SKBlog> recommendBlog = blogService.getBlogByTermAndLimit(skTerm,5,1);
        //查询表中存在的哪些年份
        List<Integer> publishYears = blogService.getBlogPublishYear();
        model.addAttribute("blogLimitList",blogLimitList);
        model.addAttribute("hotBlog",hotBlog);
        model.addAttribute("recommendBlog",recommendBlog);
        model.addAttribute("publishYears",publishYears);
        return "index";
    }

    /**
     * 全量博客数据分页查询数据
     * @param currPage
     * @return
     */
    @GetMapping("/page/{currPage}")
    @ResponseBody
    public Object getLimitBlog(@PathVariable("currPage") Integer currPage){
        SKTerm skTerm = new SKTerm();
        skTerm.setStatus(true);
        return blogService.getBlogByTermAndLimit(skTerm,PAGE_SIZE,currPage);
    }

    /**
     * 对应标签分页查询数据
     * @param labelId
     * @param currPage
     * @return
     */
    @GetMapping("/page/label/{labelId}/{currPage}")
    @ResponseBody
    public Object getLabelLimitBlog(@PathVariable("labelId") Integer labelId,@PathVariable("currPage") Integer currPage){
        SKTerm skTerm = new SKTerm();
        skTerm.setStatus(true);
        skTerm.setLabelId(labelId);
        return blogService.getBlogByTermAndLimit(skTerm,PAGE_SIZE,currPage);
    }

    /**
     * 对应分类分页查询数据
     * @param typeId
     * @param currPage
     * @return
     */
    @GetMapping("/page/type/{typeId}/{currPage}")
    @ResponseBody
    public Object getLimitBlog(@PathVariable("typeId") Integer typeId,@PathVariable("currPage") Integer currPage){
        SKTerm skTerm = new SKTerm();
        skTerm.setStatus(true);
        skTerm.setTypeId(typeId);
        return blogService.getBlogByTermAndLimit(skTerm,PAGE_SIZE,currPage);
    }

    /**
     * 按照年份及月份分页查询数据
     * @param year
     * @param month
     * @param currPage
     * @return
     */
    @GetMapping("/page/time/{year}/{month}/{currPage}")
    @ResponseBody
    public Object getLimitBlog(@PathVariable("year") Integer year,@PathVariable("month") Integer month,@PathVariable("currPage") Integer currPage){
        SKTerm skTerm = new SKTerm();
        skTerm.setStatus(true);
        skTerm.setPublishYear(year);
        if(month != 0){
            skTerm.setPublishMonth(month);
        }
        return blogService.getBlogByTermAndLimit(skTerm,PAGE_SIZE,currPage);
    }

    /**
     * 查询指定id博客
     * 1. 查询指定博客数据
     * 2. 查询对应博客id的评论信息并进行封装
     * @param blogId
     * @return
     */
    @GetMapping("/get/{blogId}")
    @ResponseBody
    public Object getBlogById(@PathVariable("blogId")Integer blogId,Model model){
        //查询指定博客数据
        SKBlog blog = blogService.getBlogById(blogId);
        //查询指定博客id对应的评论数据
        commentService.getCommentByBlogId();
        blog.setComments(null);
        model.addAttribute("blog",blog);
        return blog;
    }



    //封装分页数据查询
    private SKLimitResultVO setSKLimitResultVO(SKTerm skTerm,Integer totalNumber,Integer pageSize,Integer topCurrPage){
        //查询首页分页数据，默认按照发布时间进行排序
        List<SKBlog> blogList = blogService.getBlogByTermAndLimit(skTerm,pageSize,topCurrPage);
        SKLimitResultVO skLimitResultVO = new SKLimitResultVO();
        skLimitResultVO.setPageSize(pageSize);
        skLimitResultVO.setTotalNumber(totalNumber);
        skLimitResultVO.setPageIndex(topCurrPage);
        skLimitResultVO.setData(blogList);
        return skLimitResultVO;
    }

    //用于向数据库中添加测试数据100条
    private void setBlog(){
        Random random = new Random();
        for(int i=0;i<100;i++){
            SKBlog blog = new SKBlog();
            blog.setAuthor("kangtutu");
            blog.setTitle(i+"测试博客文章标题"+i);
            blog.setContent(i+"测试博客文章内容"+i);
            blog.setLabelId(random.nextInt(20)+1);
            blog.setLabelName("测试标签");
            blog.setTypeId(random.nextInt(20)+1);
            blog.setTypeName("测试类型");
            blog.setImageUrl("www.baidu.com");
            blog.setBlogNature(1);
            blog.setReadingQuantity(random.nextInt(1000));
            blog.setLikeNumInt(random.nextInt(1000));
            blogService.saveBlog(blog);
        }
    }

}
