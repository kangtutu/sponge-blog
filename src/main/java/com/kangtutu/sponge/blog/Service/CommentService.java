package com.kangtutu.sponge.blog.Service;

import com.kangtutu.sponge.blog.pojo.SKComment;

import java.util.List;

//评论信息相关控制器
public interface CommentService {

    //添加数据
    void saveComment(SKComment skComment);

    //更新评论数据
    void updateComment(SKComment skComment);

    /**
     * 查询指定博客id对应的父级评论信息
     * @param blogId 博客id
     * @param code 1-父级  2-子级
     * @return
     */
    List<SKComment> getCommentByBlogId(Integer blogId, Integer code);

    //查询指定博客id评论信息总条数
    Integer getCommentTotal(Integer blogId);

    //删除数据
    void deleteCommentById(Integer commentId);
}
