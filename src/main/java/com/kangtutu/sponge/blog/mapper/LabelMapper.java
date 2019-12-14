package com.kangtutu.sponge.blog.mapper;

import com.kangtutu.sponge.blog.pojo.sdo.SpongeLabelDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelMapper {

    //添加
    void saveLabel(SpongeLabelDO spongeLabelDO);

    //修改
    void updateLabel(SpongeLabelDO spongeLabelDO);

    //查询全量数据
    List<SpongeLabelDO> queryLabelAll(@Param("status") Boolean status);

    //查询指定id数据
    SpongeLabelDO queryLabelById(@Param("labelId")Integer labelId);

    //删除
    void deleteLabelById(@Param("labelId") Integer LabelId);

}
