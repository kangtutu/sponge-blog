package com.kangtutu.sponge.blog.Service.impl;

import com.kangtutu.sponge.blog.Service.CommentService;
import com.kangtutu.sponge.blog.mapper.CommentMapper;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResultObjectDTO saveComment(SpongeCommentDO spongeCommentDO) {
        //初始化参数
        initCommentParam(spongeCommentDO);
        commentMapper.saveComment(spongeCommentDO);
        return ResultObjectDTO.success();
    }

    @Override
    public ResultObjectDTO updateComment(SpongeCommentDO spongeCommentDO) {
        spongeCommentDO.setUpdateUser("kangtutu");
        spongeCommentDO.setUpdateTime(new Date());
        commentMapper.updateComment(spongeCommentDO);
        return ResultObjectDTO.success();
    }

    @Override
    public ResultObjectDTO getCommentByTerm(SpongeTermDO spongeTermDO) {
        List<SpongeCommentDO> comments = commentMapper.queryCommentByTerm(spongeTermDO);
        return ResultObjectDTO.success(comments);
    }

    @Override
    public ResultObjectDTO getCommentCountByTerm(SpongeTermDO spongeTermDO) {
        Integer count = commentMapper.queryCommentCountByTerm(spongeTermDO);
        return ResultObjectDTO.success(count);
    }

    @Override
    public ResultObjectDTO getCommentParentOrChildrenByTerm(Boolean status, Integer blogId, Integer code) {
        List<SpongeCommentDO> comments = commentMapper.queryCommentParentOrChildrenByTerm(true, blogId, code);
        return ResultObjectDTO.success(comments);
    }

    @Override
    public ResultObjectDTO deleteCommentById(Integer commentId) {
        commentMapper.deleteCommentById(commentId);
        return ResultObjectDTO.success();
    }

    //初始化参数
    private void initCommentParam(SpongeCommentDO comment){
        Date date = new Date();
        String str = "admin";
        comment.setCreationUser(str);
        comment.setCreationTime(date);
        comment.setUpdateUser(str);
        comment.setUpdateTime(date);
        if(comment.getStatus() == null){
            comment.setStatus(true);
        }
        if(comment.getWaitForReply() == null){
            comment.setWaitForReply(false);
        }
    }
}

