package com.kangtutu.sponge.blog.Service;

import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLabel;

import java.util.List;

public interface LabelService {

    //新增
    void saveLabel(SKLabel skLabel);

    //修改
    void updateLabel(SKLabel skLabel);

    //查询已经启用的标签数据
    List<SKLabel> getLabelByStatus(Boolean status);

    //查询已经启用的标签数据
    List<SKLabel> getLabelAll();

    //查询已经启用的标签对应的博客数据
    List<SKBlog> queryBlogByLabelStatus(Boolean status);

    //删除
    void deleteLabelById(Integer labelId);
}
