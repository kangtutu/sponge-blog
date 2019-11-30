package com.kangtutu.sponge.blog.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
public class SKUser implements Serializable {
    private Integer userId;
    private String account; //账号
    private String password; //密码
    private String nickName; //用户昵称
    private Integer userLevel; //用户等级
    private Integer vipLevel; //会员等级
    private Integer userLevelEmpiricalValue; //用户等级经验值
    private Integer vipLevelEmpiricalValue; //会员等级经验值
    private Integer userRight; //用户权限【独立建表对应用户权限】
    private Boolean status; //状态 0-启动 1-禁用 2-停用
    private String creationUser; //创建者
    private Date creationTime; //创建时间
    private String updateUser; //更新者
    private Date updateTime; //更新时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getUserLevelEmpiricalValue() {
        return userLevelEmpiricalValue;
    }

    public void setUserLevelEmpiricalValue(Integer userLevelEmpiricalValue) {
        this.userLevelEmpiricalValue = userLevelEmpiricalValue;
    }

    public Integer getVipLevelEmpiricalValue() {
        return vipLevelEmpiricalValue;
    }

    public void setVipLevelEmpiricalValue(Integer vipLevelEmpiricalValue) {
        this.vipLevelEmpiricalValue = vipLevelEmpiricalValue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public Integer getUserRight() {
        return userRight;
    }

    public void setUserRight(Integer userRight) {
        this.userRight = userRight;
    }

    @Override
    public String toString() {
        return "SKUser{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userLevel=" + userLevel +
                ", vipLevel=" + vipLevel +
                ", userLevelEmpiricalValue=" + userLevelEmpiricalValue +
                ", vipLevelEmpiricalValue=" + vipLevelEmpiricalValue +
                ", userRight=" + userRight +
                ", status=" + status +
                ", creationUser='" + creationUser + '\'' +
                ", creationTime=" + creationTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}

