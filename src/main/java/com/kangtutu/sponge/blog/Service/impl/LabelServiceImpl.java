package com.kangtutu.sponge.blog.Service.impl;

import com.kangtutu.sponge.blog.Service.LabelService;
import com.kangtutu.sponge.blog.mapper.LabelMapper;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeLabelDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    /**
     * 新增数据
     * @param spongeLabelDO
     * @return
     */
    @Override
    public ResultObjectDTO saveLabel(SpongeLabelDO spongeLabelDO) {
        initTypeParam(spongeLabelDO);
        labelMapper.saveLabel(spongeLabelDO);
        return ResultObjectDTO.success();
    }

    /**
     * 更新数据
     * @param spongeLabelDO
     * @return
     */
    @Override
    public ResultObjectDTO updateLabel(SpongeLabelDO spongeLabelDO) {
        spongeLabelDO.setUpdateUser("kangtutu");
        spongeLabelDO.setUpdateTime(new Date());
        return ResultObjectDTO.success();
    }

    /**
     * 查询全部
     * @param status
     * @return
     */
    @Override
    public ResultObjectDTO getLabelAll(Boolean status) {
        List<SpongeLabelDO> labels = labelMapper.queryLabelAll(true);
        return ResultObjectDTO.success(labels);
    }

    /**
     * 删除数据
     * @param labelId
     * @return
     */
    @Override
    public ResultObjectDTO deleteLabelById(Integer labelId) {
        labelMapper.deleteLabelById(labelId);
        return ResultObjectDTO.success();
    }

    //初始化参数
    private void initTypeParam(SpongeLabelDO spongeLabelDO){
        Date date = new Date();
        String str = "admin";
        if(spongeLabelDO.getStatus() == null){
            spongeLabelDO.setStatus(true);
        }
        spongeLabelDO.setCreationUser(str);
        spongeLabelDO.setCreationTime(date);
        spongeLabelDO.setUpdateUser(str);
        spongeLabelDO.setUpdateTime(date);
    }
}
