package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
import com.kangtutu.sponge.blog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//后台文章操作
@Controller
@RequestMapping("/bg/comment")
public class BackgroundCommentController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundCommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String comment(Model model){
       log.info("[后台评论留言]");
       //查询所有标签
        ResultObjectDTO result = commentService.getCommentByTerm(null);
        if(result.getCode() != 200){
            model.addAttribute("code",500);
            model.addAttribute("message","查询评论信息失败");
            return "error";
        }
        model.addAttribute("comments",result.getData());
        return "background/comment";
    }

    @GetMapping("/toUpdate/{commentId}")
    public String toUpdate(@PathVariable("commentId") Integer commentId,Model model){
        ResultObjectDTO result = commentService.getCommentById(commentId);
        if(result.getCode() != 200){
            return "error";
        }
        model.addAttribute("comment",result.getData());
        return "background/commentEditor";
    }

    @PostMapping("/update")
    public String updateComment(SpongeCommentDO spongeCommentDO){
        Integer parentCommentId = spongeCommentDO.getCommentId();
        //先保存回复信息
        SpongeCommentDO hComment = spongeCommentDO;
        hComment.setCommentId(null);
        hComment.setParentCommentId(parentCommentId);
        hComment.setWaitForReply(true);
        ResultObjectDTO resultSave = commentService.saveComment(hComment);
        if(resultSave.getCode() != 200){
            return "error";
        }
        //更新父级评论信息的回复状态
        SpongeCommentDO parentComment = new SpongeCommentDO();
        parentComment.setCommentId(parentCommentId);
        parentComment.setWaitForReply(true);
        ResultObjectDTO resultParent = commentService.updateComment(parentComment);
        if(resultParent.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/comment";
    }

    /**
     * 删除指定id评论数据
     * @param commentId
     * @return
     */
    @GetMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId){
        //查询当前id数据
        ResultObjectDTO commentById = commentService.getCommentById(commentId);
        //获取查询到评论数据对象
        SpongeCommentDO c = (SpongeCommentDO) commentById.getData();
        //若时已回复并且父级评论id不为空则需要将父级评论信息的回复标识修改为未回复
        if(c.getWaitForReply() && c.getParentCommentId() != null){
            SpongeCommentDO cc = new SpongeCommentDO();
            cc.setCommentId(c.getParentCommentId());
            cc.setWaitForReply(false);
            ResultObjectDTO resultObjectDTO = commentService.updateComment(cc);
            if(resultObjectDTO.getCode() != 200){
                return "error";
            }
        }
        ResultObjectDTO result = commentService.deleteCommentById(commentId);
        if(result.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/comment";
    }
}
