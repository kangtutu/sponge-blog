package com.kangtutu.sponge.blog.Service;

import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKTerm;

import java.util.List;

public interface BlogService {

    /**
     * 查询指定id博客
     * @param blogId 博客id
     * @return
     */
    SKBlog getBlogById(Integer blogId);

    /**
     * 按条件查询，支持分页查询
     * @param skTerm 条件对象
     * @return
     */
    List<SKBlog> getBlogByTerm(SKTerm skTerm);

    /**
     * 查询热门文章
     * @param pageSize
     * @param topCurrPage
     * @return
     */
    List<SKBlog> getHotBlogByReadingQuantity(Integer pageSize,Integer topCurrPage);

    /**
     * 查询总条数
     * @return
     */
    Integer getBlogTotal();

    /**
     * 按条件查询总数
     * @param skTerm
     * @return
     */
    Integer getBlogByTermAndTotal(SKTerm skTerm);

    /**
     * 查询表中有哪些年份的数据
     * @return
     */
    List<Integer> getBlogPublishYear();

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
    void deleteBlogById(Integer blogId);

}
