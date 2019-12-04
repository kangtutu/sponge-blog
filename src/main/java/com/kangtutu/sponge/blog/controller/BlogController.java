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
import java.util.ArrayList;
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
     * 1. 轮播图-首页推荐文章-按照首页推荐字段进行查询取前5条
     * 2. 最新发布-按照发布时间取前10条数据
     * 3. 热门文章-按照阅读量字段进行降序排序取前10条
     * 4. 首页文章列表-按照发布时间进行排序并分页查询全量数据进行展示
     * 5. 页面中的右侧联系方式部分也可以通过数据库进行存储，实时的增删改查(后续补充)
     * 6. 标签墙(后续补充)
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
        //查询首页分页数据，默认按照发布时间进行排序
        List<SKBlog> blogList = blogService.getBlogByTerm(skTerm);
        //需要将分页数据进行封装
        SKLimitResultVO blogLimitList = setSKLimitResultVO(blogList,1);
        //最新发布
        List<SKBlog> newPublish = subList(blogList,5);
        //热门文章
        List<SKBlog> hotBlog = blogService.getHotBlogByReadingQuantity(pageSize,1);
        //首页推荐相关博客-首页轮播图部分提供跳转入口
        skTerm.setOpenHomeRecommend(true);
        skTerm.setPageSize(5);
        List<SKBlog> recommendBlog = blogService.getBlogByTerm(skTerm);

        model.addAttribute("blogLimitList",blogLimitList);
        model.addAttribute("hotBlog",hotBlog);//热门文章
        model.addAttribute("recommendBlog",recommendBlog);//首页推荐
        model.addAttribute("newPublish",newPublish);//最新发布
        //标签墙
        return "index";
    }

    /**
     * 全量博客数据分页查询数据
     * @param currPage 当前页码数
     * @return
     */
    @GetMapping("/page/{currPage}")
    @ResponseBody
    public SKLimitResultVO getLimitBlog(@PathVariable("currPage") Integer currPage){
        SKTerm skTerm = new SKTerm();
        skTerm.setPageSize(pageSize);
        int topCurrPage = (currPage-1)*pageSize;//计算从第几条开始查询
        skTerm.setTopCurrPage(topCurrPage);
        List<SKBlog> blogByTerm = blogService.getBlogByTerm(skTerm);
        SKLimitResultVO skLimitResultVO = setSKLimitResultVO(blogByTerm, currPage);
        return skLimitResultVO;
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

    //截取list集合中前几条数据
    private List<SKBlog> subList(List<SKBlog> blogs,int num){
        if(blogs != null && blogs.size()>=5){
            List<SKBlog> list = new ArrayList<>();
            for(int i=0;i<num;i++){
                list.add(blogs.get(i));
            }
            return list;
        }
        return null;
    }

    //封装分页数据查询
    private SKLimitResultVO setSKLimitResultVO(List<SKBlog> blogList,Integer topCurrPage){
        //查询总条数
        Integer total = blogService.getBlogTotal();
        SKLimitResultVO skLimitResultVO = new SKLimitResultVO();
        skLimitResultVO.setPageSize(pageSize);
        //计算总页数
        int pageTotal = new BigDecimal(total).divide(new BigDecimal(pageSize),0,BigDecimal.ROUND_UP).intValue();
        skLimitResultVO.setTotalNumber(pageTotal);//总页数
        skLimitResultVO.setPageIndex(topCurrPage);//当前页数
        skLimitResultVO.setData(blogList);
        if(topCurrPage>=pageTotal){
            skLimitResultVO.setLastPage(false);
        }
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
