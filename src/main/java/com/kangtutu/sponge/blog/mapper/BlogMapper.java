package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKTerm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {

    /**
     * 查询指定id博客
     * @param blogId 博客id
     * @return
     */
    SKBlog querySKBlogById(@Param("blogId")Integer blogId);

    /**
     * 按条件查询，支持分页查询
     * @param skTerm 条件对象
     * @return
     */
    List<SKBlog> queryBlogByTerm(SKTerm skTerm);

    /**
     * 查询热门文章
     * @param pageSize 每页显示条数
     * @param topCurrPage 从第几条开始
     * @return
     */
    List<SKBlog> queryHotBlogByReadingQuantity(@Param("status") Boolean status,@Param("pageSize") Integer pageSize,@Param("topCurrPage") Integer topCurrPage);

    /**
     * 查询总条数
     * @param status
     * @return
     */
    Integer queryBlogTotal(@Param("status") Boolean status);

    /**
     * 按条件查询总数
     * @param skTerm
     * @return
     */
    Integer queryBlogByTermAndTotal(SKTerm skTerm);

    /**
     * 查询表中有哪些年份的数据
     * @return
     */
    List<Integer> queryBlogPublishYear();

    /**
     * 按照年份与月份查询全量数据并按照年份及月份进行降序排序
     * @param skTerm
     * @return
     */
    List<SKBlog> queryBlogByYearAndMonth(SKTerm skTerm);

    /**
     * 新增博客数据
     * @param skBlog
     */
    void saveBlog(SKBlog skBlog);

    /**
     * 更新博客数据
     * @param skBlog
     */
    void updateBlog(SKBlog skBlog);

    /**
     * 删除博客数据
     * @param blogId
     */
    void deleteBlogById(@Param("blogId")Integer blogId);
}
