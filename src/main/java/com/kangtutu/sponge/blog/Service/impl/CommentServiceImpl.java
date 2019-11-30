package com.kangtutu.sponge.blog.Service.impl;

import com.kangtutu.sponge.blog.Service.CommentService;
import com.kangtutu.sponge.blog.mapper.CommentMapper;
import com.kangtutu.sponge.blog.pojo.SKComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public void saveComment(SKComment skComment) {
        //初始化参数
        initParam(skComment);
        commentMapper.saveComment(skComment);
    }

    @Override
    public void updateComment(SKComment skComment) {
        skComment.setUpdateUser("kangtutu");
        skComment.setUpdateTime(new Date());
        commentMapper.updateComment(skComment);
    }

    @Override
    public List<SKComment> getCommentByBlogId(Integer blogId, Integer code) {
        //获取父级评论信息

        //获取子级评论信息

        List<Map<String,Object>> comments = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        //父级评论信息 k-parent
        map.put("parent",new SKComment());
        //子级评论信息 k-son
        List<SKComment> son = new ArrayList<>();
        map.put("son",son);
        comments.add(map);
        return commentMapper.queryParentCommentByBlogId(blogId,code);
    }

    @Override
    public Integer getCommentTotal(Integer blogId) {
        return commentMapper.queryCommentTotal(blogId);
    }

    @Override
    public void deleteCommentById(Integer commentId) {
        commentMapper.deleteCommentById(commentId);
    }

    //初始化参数
    private void initParam(SKComment skComment){
        Date date = new Date();
        String str = "admin";
        skComment.setCreationUser(str);
        skComment.setCreationTime(date);
        skComment.setUpdateUser(str);
        skComment.setUpdateTime(date);
        skComment.setStatus(true);
        skComment.setWaitForReply(false);
    }
}
