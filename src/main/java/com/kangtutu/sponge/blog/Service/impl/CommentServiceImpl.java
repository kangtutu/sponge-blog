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

    //按照分页查询
    @Override
    public List<Map<String,Object>> getCommentByBlogId(Integer blogId) {
        //获取父级评论信息
        List<SKComment> parentComment = commentMapper.queryCommentByBlogId(blogId, 1);
        //获取子级评论信息
        List<SKComment> sonComment = commentMapper.queryCommentByBlogId(blogId, 2);
        List<Map<String,Object>> comments = new ArrayList<>();
        //调用方法处理评论数据
        commentByBlogId(comments,parentComment,sonComment);
        return comments;
    }

    @Override
    public Integer getCommentTotal(Integer blogId) {
        return commentMapper.queryCommentTotal(blogId);
    }

    @Override
    public void deleteCommentById(Integer commentId) {
        commentMapper.deleteCommentById(commentId);
    }

    //封装评论信息
    private void commentByBlogId(List<Map<String,Object>> comments,List<SKComment> parentComment,List<SKComment> sonComment){
        if(parentComment!=null && parentComment.size()>0){
            for(int i=0;i<parentComment.size();i++){
                SKComment parent = parentComment.get(i);
                Map<String,Object> map = new HashMap<>();
                //父级评论信息 k-parent
                map.put("parent",parent);
                if(sonComment!=null && sonComment.size()>0){
                    List<SKComment> sonList = new ArrayList<>();
                    for(int k=0;k<sonComment.size();k++){
                        SKComment son = sonComment.get(k);
                        if(parent.getCommentId() == son.getParentCommentId()){
                            sonList.add(son);
                        }
                    }
                    //子级评论信息 k-son
                    map.put("son",sonList);
                    comments.add(map);
                }
            }
        }
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
