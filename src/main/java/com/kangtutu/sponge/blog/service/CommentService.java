package com.kangtutu.sponge.blog.service;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeCommentDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;

/**
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
public interface CommentService {

    /**
     * 增加数据
     * @param spongeCommentDO
     */
    ResultObjectDTO saveComment(SpongeCommentDO spongeCommentDO);

    /**
     * 修改数据
     * @param spongeCommentDO
     */
    ResultObjectDTO updateComment(SpongeCommentDO spongeCommentDO);

    /**
     * 按条件查询
     * @param spongeTermDO
     * @return
     */
    ResultObjectDTO getCommentByTerm(SpongeTermDO spongeTermDO);

    /**
     * 按条件查询总条数
     * @param spongeTermDO
     * @return
     */
    ResultObjectDTO getCommentCountByTerm(SpongeTermDO spongeTermDO);

    /**
     * 按照id查询指定评论信息
     * @param commentId
     * @return
     */
    ResultObjectDTO getCommentById(Integer commentId);

    /**
     * 查询博客数据的父级信息
     * @param blogId
     * @return
     */
    ResultObjectDTO getParentComment(Integer blogId);

    /**
     * 查询博客数据的子级信息
     * @param blogId
     * @return
     */
    ResultObjectDTO getChildrenComment(Integer blogId,Integer parentCommentId);

    /**
     * 删除数据
     * @param blogId
     */
    ResultObjectDTO deleteCommentByBlogId(Integer blogId);

    /**
     * 删除数据
     * @param commentId
     */
    ResultObjectDTO deleteCommentById(Integer commentId);
}
