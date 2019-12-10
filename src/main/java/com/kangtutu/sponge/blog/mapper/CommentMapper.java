package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论相关
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
public interface CommentMapper {

    /**
     * 增加数据
     * @param spongeCommentDO
     */
    void saveComment(SpongeCommentDO spongeCommentDO);

    /**
     * 修改数据
     * @param spongeCommentDO
     */
    void updateComment(SpongeCommentDO spongeCommentDO);

    /**
     * 按条件查询
     * @param spongeTermDO
     * @return
     */
    List<SpongeCommentDO> queryCommentByTerm(SpongeTermDO spongeTermDO);

    /**
     * 按条件查询总条数
     * @param spongeTermDO
     * @return
     */
    Integer queryCommentCountByTerm(SpongeTermDO spongeTermDO);

    /**
     * 查询博客数据的父级或子级信息
     * @param status
     * @param blogId
     * @param code 1-父级 2-子级
     * @return
     */
    List<SpongeCommentDO> queryCommentParentOrChildrenByTerm(@Param("status") Boolean status,@Param("blogId") Integer blogId,@Param("code") Integer code);

    /**
     * 删除数据
     * @param commentId
     */
    void deleteCommentById(@Param("commentId") Integer commentId);

}
