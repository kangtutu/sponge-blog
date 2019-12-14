package com.kangtutu.sponge.blog.service;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTypeDO;

public interface TypeService {

    //添加
    ResultObjectDTO saveType(SpongeTypeDO spongeTypeDO);

    //修改
    ResultObjectDTO updateType(SpongeTypeDO spongeTypeDO);

    //查询全量数据
    ResultObjectDTO getTypeAll(Boolean status);

    //根据id查询指定信息
    ResultObjectDTO getTypeById(Integer typeId);

    //删除
    ResultObjectDTO deleteTypeById(Integer typeId);

}
