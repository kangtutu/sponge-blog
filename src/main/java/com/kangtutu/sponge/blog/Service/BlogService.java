package com.kangtutu.sponge.blog.Service;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;

import java.util.List;

public interface BlogService {

    /**
     * 新增博客数据
     * @param spongeBlogDO
     */
    ResultObjectDTO saveBlog(SpongeBlogDO spongeBlogDO);

    /**
     * 更新博客数据
     * @param spongeBlogDO
     */
    ResultObjectDTO updateBlog(SpongeBlogDO spongeBlogDO);

    /**
     * 查询指定id博客
     * @param blogId 博客id
     * @return
     */
    ResultObjectDTO getBlogById(Integer blogId);

    /**
     * 查询阅读量排行前10的文章，按照阅读数及发布时间进行降序排序
     * @param spongeTermDO
     * @return
     */
    ResultObjectDTO getBlogByReadingQuantity(SpongeTermDO spongeTermDO);

    /**
     * 按照标签id查询
     * @param spongeTermDO
     * @return
     */
    ResultObjectDTO getBlogByLabelOrTypeByTerm(SpongeTermDO spongeTermDO);

    /**
     * 按条件查询总条数
     * @param spongeTermDO
     * @return
     */
    ResultObjectDTO getCountByTerm(SpongeTermDO spongeTermDO);

    /**
     * 删除博客数据
     * @param blogId
     */
    ResultObjectDTO deleteBlogById(Integer blogId);

}
