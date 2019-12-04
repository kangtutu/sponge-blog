package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper {

    //新增
    void saveType(SKType SKType);

    //修改
    void updateType(SKType SKType);

    //查询指定id数据
    SKType queryTypeById(@Param("TypeId") Integer TypeId);

    //查询已经启用的标签数据
    List<SKType> queryTypeByStatus(@Param("status") Boolean status);

    //查询全量数据
    List<SKType> queryTypeAll();

    //查询已经启用的标签对应的博客数据
    List<SKBlog> queryBlogByTypeStatus(@Param("status") Boolean status);

    //删除
    void deleteTypeById(@Param("TypeId") Integer TypeId);

}
