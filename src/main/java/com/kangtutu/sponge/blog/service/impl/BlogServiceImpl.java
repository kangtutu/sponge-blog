package com.kangtutu.sponge.blog.service.impl;

import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.mapper.BlogMapper;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//博客文章服务层
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    //添加数据
    @Override
    public ResultObjectDTO saveBlog(SpongeBlogDO spongeBlogDO) {
        //初始化参数
        initBlogParam(spongeBlogDO);
        blogMapper.saveBlog(spongeBlogDO);
        return ResultObjectDTO.success();
    }

    //更新数据
    @Override
    public ResultObjectDTO updateBlog(SpongeBlogDO spongeBlogDO) {
        spongeBlogDO.setUpdateUser("kangtutu");
        spongeBlogDO.setUpdateTime(new Date());
        blogMapper.updateBlog(spongeBlogDO);
        return ResultObjectDTO.success();
    }

    //通过id查询
    @Override
    public ResultObjectDTO getBlogById(Integer blogId) {
        SpongeBlogDO spongeBlogDO = blogMapper.queryBlogById(blogId);
        return ResultObjectDTO.success(spongeBlogDO);
    }

    //按照阅读数查询前10篇的文章进行页面展示
    @Override
    public ResultObjectDTO getBlogByReadingQuantity(SpongeTermDO spongeTermDO) {
        spongeTermDO.setStatus(true);
        try{
            List<SpongeBlogDO> blogs = blogMapper.queryBlogByReadingQuantity(spongeTermDO);
            return ResultObjectDTO.success(blogs);
        }catch (Exception e){
            return ResultObjectDTO.failure(e.getMessage());
        }
    }

    /**
     * 查询分类或标签对应博客数据，按照发布时间及浏览量进行降序排序分页查找
     * @param spongeTermDO
     * @return
     */
    @Override
    public ResultObjectDTO getBlogByLabelOrTypeByTerm(SpongeTermDO spongeTermDO) {
        spongeTermDO.setStatus(true);
        List<SpongeBlogDO> blogs = blogMapper.queryBlogLabelOrTypeByTerm(spongeTermDO);
        return ResultObjectDTO.success(blogs);
    }

    /**
     * 按条件查询总天数
     * @param spongeTermDO
     * @return
     */
    @Override
    public ResultObjectDTO getCountByTerm(SpongeTermDO spongeTermDO) {
        spongeTermDO.setStatus(true);
        Integer count = blogMapper.queryCountByTerm(spongeTermDO);
        return ResultObjectDTO.success(count);
    }

    /**
     * 查询数据库表中存在的归档年份
     * @return
     */
    @Override
    public ResultObjectDTO getBlogPublishYear() {
        List<Integer> years = blogMapper.queryBlogPublishYear();
        return ResultObjectDTO.success(years);
    }

    /**
     * 按条件查询文章数据
     * @param spongeTermDO
     * @return
     */
    @Override
    public ResultObjectDTO getBlogByTerm(SpongeTermDO spongeTermDO) {
        List<SpongeBlogDO> blogs = blogMapper.queryBlogByTerm(spongeTermDO);
        return ResultObjectDTO.success(blogs);
    }

    //删除数据
    @Override
    public ResultObjectDTO deleteBlogById(Integer blogId) {
        blogMapper.deleteBlogById(blogId);
        return ResultObjectDTO.success();
    }

    //初始化参数
    private void initBlogParam(SpongeBlogDO spongeBlogDO){
        Date date = new Date();
        String str = "admin";
        spongeBlogDO.setAuthor("kangtutu");
        spongeBlogDO.setReadingQuantity(0);
        spongeBlogDO.setLikeNumInt(0);
        //默认文章类型为原创
        if(spongeBlogDO.getBlogNature() == null){
            spongeBlogDO.setBlogNature(1);
        }
        spongeBlogDO.setCreationUser(str);
        spongeBlogDO.setCreationTime(date);
        spongeBlogDO.setUpdateUser(str);
        spongeBlogDO.setUpdateTime(date);
        //封装归档年月参数
        spongeBlogDO.setPublishYear(DateUtils.getYear());
        spongeBlogDO.setPublishMonth(DateUtils.getMonth());
        if(spongeBlogDO.getStatus() == null){
            spongeBlogDO.setStatus(true);
        }

        if(spongeBlogDO.getOpenHomeRecommend() == null){
            spongeBlogDO.setOpenHomeRecommend(true);
        }

        if(spongeBlogDO.getOpenComment() == null){
            spongeBlogDO.setOpenComment(true);
        }

        if(spongeBlogDO.getOpenCopyright() == null){
            spongeBlogDO.setOpenCopyright(true);
        }
    }
}
