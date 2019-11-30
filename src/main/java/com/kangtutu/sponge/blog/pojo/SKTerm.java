package com.kangtutu.sponge.blog.pojo;

import java.io.Serializable;

/**
 * 查询条件类
 * 将需要查询的多个条件统一封装在此类中进行条件判断，简化dao查询逻辑
 */
public class SKTerm implements Serializable {

    private static SKTerm skTerm = new SKTerm();

    private Integer blogId;//博客id
    private Integer labelId;//标签id
    private Integer typeId;//分类id
    private Integer commentId;//评论id
    private Integer publishYear;//发布年份
    private Integer publishMonth;//发布月份
    private Integer readingQuantity; //阅读量
    private Integer likeNumInt; //点赞量
    private Boolean waitForReply;//待回复
    private Boolean isOpenHomeRecommend; //首页推荐
    private Boolean isOpenCopyright; //版权信息
    private Boolean isOpenComment; //评论功能
    private Boolean status; //状态

    public SKTerm() {}

    public static SKTerm getInstance(){
        return skTerm;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getReadingQuantity() {
        return readingQuantity;
    }

    public void setReadingQuantity(Integer readingQuantity) {
        this.readingQuantity = readingQuantity;
    }

    public Integer getLikeNumInt() {
        return likeNumInt;
    }

    public void setLikeNumInt(Integer likeNumInt) {
        this.likeNumInt = likeNumInt;
    }

    public Boolean getOpenHomeRecommend() {
        return isOpenHomeRecommend;
    }

    public void setOpenHomeRecommend(Boolean openHomeRecommend) {
        isOpenHomeRecommend = openHomeRecommend;
    }

    public Boolean getOpenCopyright() {
        return isOpenCopyright;
    }

    public void setOpenCopyright(Boolean openCopyright) {
        isOpenCopyright = openCopyright;
    }

    public Boolean getOpenComment() {
        return isOpenComment;
    }

    public void setOpenComment(Boolean openComment) {
        isOpenComment = openComment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Boolean getWaitForReply() {
        return waitForReply;
    }

    public void setWaitForReply(Boolean waitForReply) {
        this.waitForReply = waitForReply;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getPublishMonth() {
        return publishMonth;
    }

    public void setPublishMonth(Integer publishMonth) {
        this.publishMonth = publishMonth;
    }
}
