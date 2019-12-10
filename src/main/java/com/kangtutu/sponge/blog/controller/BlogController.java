package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.Service.CommentService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

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

    @GetMapping("/index")
    public Object list(Model model) {
        log.info("[博客首页] 进入博客首页方法内");
        //查询页面需要展示的博客文章
        SpongeTermDO term = SpongeTermDO.getInstance();
        term.setStartPage(0);
        term.setPageSize(pageSize);
        ResultObjectDTO resultObjectDTO = blogService.getBlogByReadingQuantity(term);
        log.info("[博客首页] 查询到的博客列表数据,{}",resultObjectDTO);
        if(!resultObjectDTO.getStatus()){
            log.info("[博客首页] 查询博客列表数据失败");
            model.addAttribute("code",ResultCodeEnumVO.FAILURE.getCode());
            model.addAttribute("message","查询首页列表数据失败");
            return "error";
        }
        model.addAttribute("blogs",resultObjectDTO.getData());
        return "index";
    }

    @GetMapping("/get/{blogId}")
    public Object getBlogById(@PathVariable("blogId")Integer blogId,Model model){
        log.info("[查询指定ID博客] 进入方法内,ID参数:{}",blogId);
        ResultObjectDTO resultObjectDTO = blogService.getBlogById(blogId);
        if(!resultObjectDTO.getStatus()){
            log.info("[查询指定ID博客] 查询文章ID:"+blogId+" 数据失败");
            model.addAttribute("code",ResultCodeEnumVO.FAILURE.getCode());
            model.addAttribute("message","查询文章ID:"+blogId+"数据失败");
            return "error";
        }
        log.info("[查询指定ID博客] 查询文章ID:"+blogId+"数据成功,{}",resultObjectDTO.getData());
        //封装指定博客ID的评论信息
        ResultObjectDTO parentComments = commentService.getCommentParentOrChildrenByTerm(true, blogId, 1);//查询父级评论信息
        ResultObjectDTO childrenComments = commentService.getCommentParentOrChildrenByTerm(true, blogId, 2);//查询子级评论信息
        //创建评论信息封装对象
        List<Map<String,Object>> list = new ArrayList<>();
        //父级评论id与子级评论id进行比对筛选
        List<SpongeCommentDO> parent = (List<SpongeCommentDO>) parentComments.getData();
        List<SpongeCommentDO> children = (List<SpongeCommentDO>) childrenComments.getData();
        if(parentComments != null && parent.size()>0){
            for(int i=0;i<parent.size();i++){
                Map<String,Object> map = new HashMap<>();
                SpongeCommentDO parentComment = parent.get(i);
                map.put("parent",parentComment);
                if(childrenComments != null && children.size()>0){
                    List<SpongeCommentDO> childrenList = new ArrayList<>();
                    for(int k=0;k<children.size();k++){
                        SpongeCommentDO childrenComment = children.get(k);
                        if(parentComment.getCommentId() == childrenComment.getParentCommentId()){
                            childrenList.add(childrenComment);
                        }
                    }
                    map.put("children",childrenList);
                    list.add(map);
                }
            }
        }
        System.out.println(parent);
        model.addAttribute("blog",resultObjectDTO.getData());
        return "blog";
    }

}
