package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.service.CommentService;
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

        //查询评论信息并进行封装
        List<Map<String, Object>> comments = setCommentParentAndChildren(blogId);
        log.info("[查询指定ID博客] 查询文章ID:"+blogId+"的评论信息数据为:{}",comments);

        SpongeTermDO term = new SpongeTermDO();
        term.setBlogId(blogId);
        ResultObjectDTO countComment = commentService.getCommentCountByTerm(term);

        model.addAttribute("blog",resultObjectDTO.getData());
        model.addAttribute("countComment",countComment.getData());
        model.addAttribute("comments",comments);
        return "blog";
    }

    //封装评论信息
    private List<Map<String,Object>> setCommentParentAndChildren(Integer blogId){
        log.info("进入封装评论信息方法内,博客ID:{}",blogId);
        List<Map<String,Object>> list = new ArrayList<>();
        //根据博客id获取父级评论信息
        ResultObjectDTO parentComment = commentService.getParentComment(blogId);
        log.info("服务层查询返回的父级评论信息数据为:{}",parentComment);
        Object parent = parentComment.getData();
        if(parentComment.getCode() == 200 && parent != null){
            List<SpongeCommentDO> pComment = (List<SpongeCommentDO>)parent;
            //根据博客id获取子级评论信息
            if(pComment != null && pComment.size()>0){
                for(int i=0;i<pComment.size();i++){
                    //封装父级评论信息
                    Map<String,Object> map = new HashMap<>();
                    SpongeCommentDO comment = pComment.get(i);
                    map.put("parent",comment);
                    //查询子级评论信息
                    ResultObjectDTO childrenComment = commentService.getChildrenComment(blogId, comment.getCommentId());
                    log.info("服务层查询返回的子级评论信息数据为:{}",childrenComment);
                    Object children = childrenComment.getData();
                    if(childrenComment.getCode() == 200 && children != null){
                        List<SpongeCommentDO> cComment = (List<SpongeCommentDO>)children;
                        if(cComment != null && cComment.size()>0){
                            map.put("children",cComment);
                        }
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }

}
