package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.Service.CommentService;
import com.kangtutu.sponge.blog.exception.custom.ParameterCalibrationException;
import com.kangtutu.sponge.blog.mapper.CommentMapper;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLimitResultVO;
import com.kangtutu.sponge.blog.pojo.SKTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/blog")
public class BlogController {

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

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
    public String list(Model model) {
        //查询总条数
        Integer total = blogService.getBlogTotal();
        //创建条件对象
        SKTerm skTerm = new SKTerm();
        skTerm.setPageSize(pageSize);
        skTerm.setTopCurrPage(0);
        //需要将分页数据进行封装
        SKLimitResultVO blogLimitList = setSKLimitResultVO(skTerm, total,pageSize,1);
        //热门文章-首页轮播图部分提供跳转入口，查询数据只要阅读量排序的前5条
        List<SKBlog> hotBlog = blogService.getHotBlogByReadingQuantity(5,1);
        //首页推荐相关博客
        skTerm.setOpenHomeRecommend(true);
        skTerm.setPageSize(5);
        List<SKBlog> recommendBlog = blogService.getBlogByTerm(skTerm);
        System.out.println(recommendBlog);
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
        skTerm.setPageSize(pageSize);
        int topCurrPage = (currPage-1)*pageSize;//计算从第几条开始查询
        skTerm.setTopCurrPage(topCurrPage);
        return blogService.getBlogByTerm(skTerm);
    }

    /**
     * 对应标签与分类分页查询数据
     * @param name
     * @param id
     * @param currPage
     * @return
     */
    @GetMapping("/page/{name}/{Id}/{currPage}")
    @ResponseBody
    public Object getLabelLimitBlog(@PathVariable("name") Integer name,@PathVariable("id") Integer id,@PathVariable("currPage") Integer currPage){
        /**
         * name参数作为灵活参数，当此参数为null时则查询全量的博客文章
         * 1. 当name值为label时则查询对应的标签博客数据，id为对应的标签id
         * 2. 当name值为type时则查询对应的分类博客数据，id为对应的类型id
         */
        SKTerm skTerm = new SKTerm();
        if(name.equals("label")){
            skTerm.setLabelId(id);
        }
        if (name.equals("type")){
            skTerm.setTypeId(id);
        }
        skTerm.setPageSize(pageSize);
        int topCurrPage = (currPage-1)*pageSize;//计算从第几条开始查询
        skTerm.setTopCurrPage(topCurrPage);

        return blogService.getBlogByTerm(skTerm);
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
        skTerm.setPageSize(pageSize);
        int topCurrPage = (currPage-1)*pageSize;//计算从第几条开始查询
        skTerm.setTopCurrPage(topCurrPage);

        return blogService.getBlogByTerm(skTerm);
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
        commentService.getCommentByBlogId(blogId);
        blog.setComments(null);
        model.addAttribute("blog",blog);
        return blog;
    }



    //封装分页数据查询
    private SKLimitResultVO setSKLimitResultVO(SKTerm skTerm,Integer total,Integer pageSize,Integer topCurrPage){
        //查询首页分页数据，默认按照发布时间进行排序
        List<SKBlog> blogList = blogService.getBlogByTerm(skTerm);
        SKLimitResultVO skLimitResultVO = new SKLimitResultVO();
        skLimitResultVO.setPageSize(pageSize);
        int pageTotal = new BigDecimal(total).divide(new BigDecimal(pageSize),0,BigDecimal.ROUND_UP).intValue();
        skLimitResultVO.setTotalNumber(pageTotal);//总页数
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
            Random ran = new Random();
            int a = ran.nextInt(4);
            int y = 2019;
            int m = random.nextInt(11)+1;
            if(a==0){
                y=2017;
            }else if(a==1){
                y=2016;
            }else if(a==2){
                y=2018;
            }
            blog.setPublishYear(y);
            blog.setPublishMonth(m);
            blog.setReadingQuantity(random.nextInt(1000));
            blog.setLikeNumInt(random.nextInt(1000));
            blogService.saveBlog(blog);
        }
    }

}
