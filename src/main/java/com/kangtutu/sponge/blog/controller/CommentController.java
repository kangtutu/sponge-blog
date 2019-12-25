package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.service.CommentService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog/comment")
@Slf4j
@Api(tags = "[前台] 评论信息相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public SpongeResultVO addComment(SpongeCommentDO spongeCommentDO){
        ResultObjectDTO resultObjectDTO = commentService.saveComment(spongeCommentDO);
        if(resultObjectDTO.getCode() != 200){
            return SpongeResultVO.failure();
        }
        return SpongeResultVO.success();
    }

    @GetMapping("/get/{blogId}")
    public SpongeResultVO getCommentByBlogId(@PathVariable("blogId") Integer blogId){
        log.info("[查询指定博客ID评论信息] 查询文章ID:"+blogId);
        try{
            List<Map<String, Object>> comments = setCommentParentAndChildren(blogId);
            log.info("[查询指定博客ID评论信息] 评论信息数据为:{}",comments);
            return SpongeResultVO.success(comments);
        }catch (Exception e){
            log.info("[查询指定博客ID评论信息] 查询评论信息失败");
            return SpongeResultVO.failure();
        }
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
