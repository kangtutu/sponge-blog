package com.kangtutu.sponge.blog.pojo.sdo;

import java.io.Serializable;
import java.util.Date;

/**
 * 基于RBAC模型设计以下三张表：
 * 用户表
 * 用户角色表
 * 用户权限表
 *
 * 用户表 -> 用户角色表 -> 用户权限表
 */
public class SpongeSecurityUserDO implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Boolean accountNonExpired; //是否过期
    private Boolean accountNonLocked; //是否锁定
    private Boolean enabled;//是否可用
    private Boolean credentialsNonExpired;//证书是否过期
    private Date creationTime; //创建日期
    private Date lastLoginTime; //最后登录日期

    @Override
    public String toString() {
        return "SpongeSecurityUserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", enabled=" + enabled +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", creationTime=" + creationTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
