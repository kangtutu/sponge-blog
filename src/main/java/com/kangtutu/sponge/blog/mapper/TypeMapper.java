package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.sdo.SpongeTypeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper {

    //添加
    void saveType(SpongeTypeDO spongeTypeDO);

    //修改
    void updateType(SpongeTypeDO spongeTypeDO);

    //查询全量数据
    List<SpongeTypeDO> queryTypeAll(@Param("status") Boolean status);

    //删除
    void deleteTypeById(@Param("labelId") Integer LabelId);

}
