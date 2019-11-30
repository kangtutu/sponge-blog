package com.kangtutu.sponge.blog.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
public class SKComment implements Serializable {

    private Integer commentId;//评论ID
    private Integer blogId; //对应的博客文章
    private String title; //博客标题，页面展示时需限制字数
    private Integer parentCommentId; //父级评论信息ID
    private String obServer; //评论者
    private String headAddress;//头像地址
    private String content; //内容
    private Boolean waitForReply;//待回复 已回复 - true  未回复 - false
    private Boolean status; //状态
    private String creationUser; //创建者
    private Date creationTime; //创建时间
    private String updateUser; //更新者
    private Date updateTime; //更新时间

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getObServer() {
        return obServer;
    }

    public void setObServer(String obServer) {
        this.obServer = obServer;
    }

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Boolean getWaitForReply() {
        return waitForReply;
    }

    public void setWaitForReply(Boolean waitForReply) {
        this.waitForReply = waitForReply;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

