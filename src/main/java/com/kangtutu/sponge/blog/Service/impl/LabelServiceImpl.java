package com.kangtutu.sponge.blog.Service.impl;

import com.kangtutu.sponge.blog.Service.LabelService;
import com.kangtutu.sponge.blog.mapper.LabelMapper;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    /**
     * 新增数据
     * @param skLabel
     */
    @Override
    public void saveLabel(SKLabel skLabel) {
        //初始化参数
        initLabel(skLabel);
        labelMapper.saveLabel(skLabel);
    }

    /**
     * 更新数据
     * @param skLabel
     */
    @Override
    public void updateLabel(SKLabel skLabel) {
        Date date = new Date();
        String updateUser = "kangtutu";
        skLabel.setUpdateUser(updateUser);
        skLabel.setUpdateTime(date);
        labelMapper.updateLabel(skLabel);
    }

    /**
     * 查询指定状态的标签数据
     * @param status
     * @return
     */
    @Override
    public List<SKLabel> getLabelByStatus(Boolean status) {
        return labelMapper.queryLabelByStatus(status);
    }

    /**
     * 查询全量的标签数据
     * @return
     */
    @Override
    public List<SKLabel> getLabelAll() {
        return labelMapper.queryLabelAll();
    }

    /**
     * 查询指定状态的博客数据
     * @param status 状态值
     * @return
     */
    @Override
    public List<SKBlog> queryBlogByLabelStatus(Boolean status) {
        return labelMapper.queryBlogByLabelStatus(status);
    }

    /**
     * 删除指定id数据
     * @param labelId 标签id
     */
    @Override
    public void deleteLabelById(Integer labelId) {
        labelMapper.deleteLabelById(labelId);
    }

    //初始化标签对象参数
    private void initLabel(SKLabel skLabel){
        Date date = new Date();
        String user = "admin";
        skLabel.setStatus(true);
        skLabel.setCreationUser(user);
        skLabel.setCreationTime(date);
        skLabel.setUpdateUser(user);
        skLabel.setUpdateTime(date);

    }
}
