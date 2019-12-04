package com.kangtutu.sponge.blog.Service.impl;

import com.kangtutu.sponge.blog.Service.TypeService;
import com.kangtutu.sponge.blog.mapper.TypeMapper;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 新增数据
     * @param skType
     */
    @Override
    public void saveType(SKType skType) {
        //初始化参数
        initType(skType);
        typeMapper.saveType(skType);
    }

    /**
     * 更新数据
     * @param skType
     */
    @Override
    public void updateType(SKType skType) {
        Date date = new Date();
        String updateUser = "kangtutu";
        skType.setUpdateUser(updateUser);
        skType.setUpdateTime(date);
        typeMapper.updateType(skType);
    }

    /**
     * 查询指定状态的标签数据
     * @param status
     * @return
     */
    @Override
    public List<SKType> getTypeByStatus(Boolean status) {
        return typeMapper.queryTypeByStatus(status);
    }

    /**
     * 查询全量的标签数据
     * @return
     */
    @Override
    public List<SKType> getTypeAll() {
        return typeMapper.queryTypeAll();
    }

    /**
     * 查询指定状态的博客数据
     * @param status 状态值
     * @return
     */
    @Override
    public List<SKBlog> queryBlogByTypeStatus(Boolean status) {
        return typeMapper.queryBlogByTypeStatus(status);
    }

    /**
     * 删除指定id数据
     * @param TypeId 标签id
     */
    @Override
    public void deleteTypeById(Integer TypeId) {
        typeMapper.deleteTypeById(TypeId);
    }

    //初始化标签对象参数
    private void initType(SKType skType){
        Date date = new Date();
        String user = "admin";
        skType.setStatus(true);
        skType.setCreationUser(user);
        skType.setCreationTime(date);
        skType.setUpdateUser(user);
        skType.setUpdateTime(date);

    }
}
