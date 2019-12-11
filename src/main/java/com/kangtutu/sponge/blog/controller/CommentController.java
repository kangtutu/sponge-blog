package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.CommentService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public SpongeResultVO addComment(SpongeCommentDO spongeCommentDO){
        ResultObjectDTO resultObjectDTO = commentService.saveComment(spongeCommentDO);
        if(resultObjectDTO.getCode() != 200){
            return SpongeResultVO.failure();
        }
        return SpongeResultVO.success();
    }

}
