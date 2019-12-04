package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelMapper {

    //新增
    void saveLabel(SKLabel skLabel);

    //修改
    void updateLabel(SKLabel skLabel);

    //查询指定id数据
    SKLabel queryLabelById(@Param("labelId") Integer labelId);

    //查询已经启用的标签数据
    List<SKLabel> queryLabelByStatus(@Param("status") Boolean status);

    //查询全量数据
    List<SKLabel> queryLabelAll();

    //查询已经启用的标签对应的博客数据
    List<SKBlog> queryBlogByLabelStatus(@Param("status") Boolean status);

    //删除
    void deleteLabelById(@Param("labelId") Integer labelId);

}
