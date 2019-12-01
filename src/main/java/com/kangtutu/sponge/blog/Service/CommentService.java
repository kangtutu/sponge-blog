package com.kangtutu.sponge.blog.Service;

import com.kangtutu.sponge.blog.pojo.SKComment;

import java.util.List;
import java.util.Map;

//评论信息相关控制器
public interface CommentService {

    //添加数据
    void saveComment(SKComment skComment);

    //更新评论数据
    void updateComment(SKComment skComment);

    /**
     * 查询指定博客id对应的父级评论信息
     * @param blogId 博客id
     * @return
     */
    List<Map<String,Object>> getCommentByBlogId(Integer blogId);

    //查询指定博客id评论信息总条数
    Integer getCommentTotal(Integer blogId);

    //删除数据
    void deleteCommentById(Integer commentId);
}
