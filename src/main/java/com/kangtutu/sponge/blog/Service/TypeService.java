package com.kangtutu.sponge.blog.Service;

import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKType;

import java.util.List;

public interface TypeService {

    //新增
    void saveType(SKType SKType);

    //修改
    void updateType(SKType SKType);

    //查询已经启用的标签数据
    List<SKType> getTypeByStatus(Boolean status);

    //查询已经启用的标签数据
    List<SKType> getTypeAll();

    //查询已经启用的标签对应的博客数据
    List<SKBlog> queryBlogByTypeStatus(Boolean status);

    //删除
    void deleteTypeById(Integer TypeId);
}
