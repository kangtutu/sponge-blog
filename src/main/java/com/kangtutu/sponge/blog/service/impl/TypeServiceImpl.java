package com.kangtutu.sponge.blog.service.impl;

import com.kangtutu.sponge.blog.service.TypeService;
import com.kangtutu.sponge.blog.mapper.TypeMapper;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTypeDO;
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
     * @param spongeTypeDO
     * @return
     */
    @Override
    public ResultObjectDTO saveType(SpongeTypeDO spongeTypeDO) {
        initTypeParam(spongeTypeDO);
        typeMapper.saveType(spongeTypeDO);
        return ResultObjectDTO.success();
    }

    /**
     * 修改数据
     * @param spongeTypeDO
     * @return
     */
    @Override
    public ResultObjectDTO updateType(SpongeTypeDO spongeTypeDO) {
        spongeTypeDO.setUpdateUser("kangtutu");
        spongeTypeDO.setUpdateTime(new Date());
        typeMapper.updateType(spongeTypeDO);
        return ResultObjectDTO.success();
    }

    @Override
    public ResultObjectDTO getTypeAll(Boolean status) {
        List<SpongeTypeDO> types = typeMapper.queryTypeAll(status);
        return ResultObjectDTO.success(types);
    }

    @Override
    public ResultObjectDTO getTypeById(Integer typeId) {
        SpongeTypeDO type = typeMapper.queryTypeById(typeId);
        return ResultObjectDTO.success(type);
    }

    @Override
    public ResultObjectDTO deleteTypeById(Integer typeId) {
        typeMapper.deleteTypeById(typeId);
        return ResultObjectDTO.success();
    }

    //初始化参数
    private void initTypeParam(SpongeTypeDO spongeTypeDO){
        Date date = new Date();
        String str = "admin";
        if(spongeTypeDO.getStatus() == null){
            spongeTypeDO.setStatus(true);
        }
        spongeTypeDO.setCreationUser(str);
        spongeTypeDO.setCreationTime(date);
        spongeTypeDO.setUpdateUser(str);
        spongeTypeDO.setUpdateTime(date);
    }
}
