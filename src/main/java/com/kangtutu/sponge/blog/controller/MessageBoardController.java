package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.service.CommentService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog/board")
public class MessageBoardController {

    private static final Logger log = LoggerFactory.getLogger(MessageBoardController.class);

    @Autowired
    private CommentService commentService;

    //留言板首页
    @GetMapping
    public String board(Model model){
        log.info("[留言板首页] 进入留言板方法内");
        //封装评论信息
        List<Map<String, Object>> comments = setCommentParentAndChildren(99999);
        log.info("[留言板首页] 封装后的评论数据为:{}",comments);
        //查询对应的评论信息条数
        SpongeTermDO term = new SpongeTermDO();
        term.setBlogId(99999);
        ResultObjectDTO countComment = commentService.getCommentCountByTerm(term);
        log.info("[留言板首页] 查询留言板评论数据为:{}",countComment);

        model.addAttribute("countComment",countComment.getData());
        model.addAttribute("comments",comments);
        return "messageBoard";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public SpongeResultVO addComment(SpongeCommentDO spongeCommentDO){
        spongeCommentDO.setBlogId(99999);//封装留言板对应的id
        spongeCommentDO.setTitle("留言板评论数据");//封装标题内容
        ResultObjectDTO resultObjectDTO = commentService.saveComment(spongeCommentDO);
        if(resultObjectDTO.getCode() != 200){
            return SpongeResultVO.failure();
        }
        return SpongeResultVO.success();
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

