package com.kangtutu.sponge.blog.service.impl;

import com.kangtutu.sponge.blog.service.CommentService;
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

    /**
     * 新增评论
     * @param spongeCommentDO
     * @return
     */
    @Override
    public ResultObjectDTO saveComment(SpongeCommentDO spongeCommentDO) {
        //初始化参数
        initCommentParam(spongeCommentDO);
        commentMapper.saveComment(spongeCommentDO);
        return ResultObjectDTO.success();
    }

    /**
     * 修改评论数据
     * @param spongeCommentDO
     * @return
     */
    @Override
    public ResultObjectDTO updateComment(SpongeCommentDO spongeCommentDO) {
        spongeCommentDO.setUpdateUser("kangtutu");
        spongeCommentDO.setUpdateTime(new Date());
        commentMapper.updateComment(spongeCommentDO);
        return ResultObjectDTO.success();
    }

    /**
     * 按条件查询评论数据
     * @param spongeTermDO
     * @return
     */
    @Override
    public ResultObjectDTO getCommentByTerm(SpongeTermDO spongeTermDO) {
        List<SpongeCommentDO> comments = commentMapper.queryCommentByTerm(spongeTermDO);
        return ResultObjectDTO.success(comments);
    }

    /**
     * 按条件获取评论总条数
     * @param spongeTermDO
     * @return
     */
    @Override
    public ResultObjectDTO getCommentCountByTerm(SpongeTermDO spongeTermDO) {
        Integer count = commentMapper.queryCommentCountByTerm(spongeTermDO);
        return ResultObjectDTO.success(count);
    }

    /**
     * 获取父级评论信息
     * @param blogId
     * @return
     */
    @Override
    public ResultObjectDTO getParentComment(Integer blogId) {
        List<SpongeCommentDO> comments = commentMapper.queryParentComment(true, blogId);
//        if(comments!= null && comments.size()>0 && comments.get(0) != null){
//            return ResultObjectDTO.success(comments);
//        }
        return ResultObjectDTO.success(comments);
    }

    /**
     * 获取子级评论信息
     * @param blogId
     * @param parentCommentId
     * @return
     */
    @Override
    public ResultObjectDTO getChildrenComment(Integer blogId, Integer parentCommentId) {
        List<SpongeCommentDO> comments = commentMapper.queryChildrenComment(true, blogId, parentCommentId);
//        if(comments!= null && comments.size()>0 && comments.get(0) != null){
//            return ResultObjectDTO.success(comments);
//        }
        return ResultObjectDTO.success(comments);
    }

    /**
     * 删除指定博客id的全部评论信息
     * @param blogId
     * @return
     */
    @Override
    public ResultObjectDTO deleteCommentByBlogId(Integer blogId) {
        commentMapper.deleteCommentByBlogId(blogId);
        return ResultObjectDTO.success();
    }


    /**
     * 删除指定id评论
     * @param commentId
     * @return
     */
    @Override
    public ResultObjectDTO deleteCommentById(Integer commentId) {
        commentMapper.deleteCommentById(commentId);
        return ResultObjectDTO.success();
    }

    /**
     * 初始化参数
     * @param comment
     */
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

