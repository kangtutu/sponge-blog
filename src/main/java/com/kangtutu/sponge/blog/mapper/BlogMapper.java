package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {

    /**
     * 新增博客数据
     * @param spongeBlogDO
     */
    void saveBlog(SpongeBlogDO spongeBlogDO);

    /**
     * 更新博客数据
     * @param spongeBlogDO
     */
    void updateBlog(SpongeBlogDO spongeBlogDO);

    /**
     * 查询指定id博客
     * @param blogId 博客id
     * @return
     */
    SpongeBlogDO queryBlogById(@Param("blogId")Integer blogId);

    /**
     * 查询阅读量排行前10的文章，按照阅读数及发布时间进行降序排序
     * @param spongeTermDO
     * @return
     */
    List<SpongeBlogDO> queryBlogByReadingQuantity(SpongeTermDO spongeTermDO);

    /**
     * 通用条件查找方法
     * @param spongeTermDO
     * @return
     */
    List<SpongeBlogDO> queryBlogByTerm(SpongeTermDO spongeTermDO);

    /**
     * 查询分类及标签数据，按照阅读数及发布时间降序排序
     * @param spongeTermDO
     * @return
     */
    List<SpongeBlogDO> queryBlogLabelOrTypeByTerm(SpongeTermDO spongeTermDO);

    /**
     * 按条件查询总条数
     * @param spongeTermDO
     * @return
     */
    Integer queryCountByTerm(SpongeTermDO spongeTermDO);

    /**
     * 删除博客数据
     * @param blogId
     */
    void deleteBlogById(@Param("blogId")Integer blogId);
}
