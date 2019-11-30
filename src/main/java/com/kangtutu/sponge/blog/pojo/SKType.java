package com.kangtutu.sponge.blog.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
public class SKType implements Serializable {
    private Integer typeId;
    private String typeName;//分类名称
    private Integer status; //状态 0-启动 1-禁用
    private String creationUser; //创建者
    private Date creationTime; //创建时间
    private String updateUser; //更新者
    private Date updateTime; //更新时间

    @Override
    public String toString() {
        return "SKType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", status=" + status +
                ", creationUser='" + creationUser + '\'' +
                ", creationTime=" + creationTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

