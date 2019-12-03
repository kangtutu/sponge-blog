package com.kangtutu.sponge.blog.Service.impl;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.mapper.BlogMapper;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//博客文章服务层
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    //通过id查询
    @Override
    public SKBlog getBlogById(Integer blogId) {
        return blogMapper.queryBlogById(blogId);
    }

    //按条件查询
    @Override
    public List<SKBlog> getBlogByTerm(SKTerm skTerm) {
        System.out.println(skTerm);
        return blogMapper.queryBlogByTerm(skTerm);
    }

    //查询热门文章
    @Override
    public List<SKBlog> getHotBlogByReadingQuantity(Integer pageSize, Integer topCurrPage) {
        return blogMapper.queryHotBlogByReadingQuantity(true,pageSize,topCurrPage);
    }

    //查询总条数
    @Override
    public Integer getBlogTotal() {
        return blogMapper.queryBlogTotal(true);
    }

    //按条件查询总条数
    @Override
    public Integer getBlogByTermAndTotal(SKTerm skTerm) {
        return blogMapper.queryBlogByTermAndTotal(skTerm);
    }

    //查询表中存在哪些年份的数据
    @Override
    public List<Integer> getBlogPublishYear() {
        return blogMapper.queryBlogPublishYear();
    }

    //按照月份及年份进行分页查询并进行降序排序
    @Override
    public List<SKBlog> getBlogByYearAndMonth(SKTerm skTerm) {
        return blogMapper.queryBlogByYearAndMonth(skTerm);
    }

    //添加数据
    @Override
    public void saveBlog(SKBlog skBlog) {
        //初始化参数
        initParam(skBlog);
        blogMapper.saveBlog(skBlog);
    }

    //更新数据
    @Override
    public void updateBlog(SKBlog skBlog) {
        blogMapper.updateBlog(skBlog);
    }

    //删除数据
    @Override
    public void deleteBlogById(Integer blogId) {
        blogMapper.deleteBlogById(blogId);
    }

    //初始化参数
    private void initParam(SKBlog skBlog){
        Date date = new Date();
        String str = "admin";
        skBlog.setCreationUser(str);
        skBlog.setCreationTime(date);
        skBlog.setUpdateUser(str);
        skBlog.setUpdateTime(date);
        skBlog.setStatus(true);
        skBlog.setOpenHomeRecommend(true);
        skBlog.setOpenComment(true);
        skBlog.setOpenCopyright(true);
        skBlog.setBlogNature(1);
    }
}
