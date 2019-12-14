package com.kangtutu.sponge.blog.service;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeLabelDO;

public interface LabelService {

    //添加
    ResultObjectDTO saveLabel(SpongeLabelDO spongeLabelDO);

    //修改
    ResultObjectDTO updateLabel(SpongeLabelDO spongeLabelDO);

    //查询全量数据
    ResultObjectDTO getLabelAll(Boolean status);

    //根据id查询指定信息
    ResultObjectDTO getLabelById(Integer labelId);

    //删除
    ResultObjectDTO deleteLabelById(Integer labelId);

}
