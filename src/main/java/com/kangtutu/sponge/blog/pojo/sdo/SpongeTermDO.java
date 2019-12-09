package com.kangtutu.sponge.blog.pojo.sdo;

import java.io.Serializable;

public class SpongeTermDO implements Serializable {
    private static SpongeTermDO spongeTermDO = new SpongeTermDO();
    private Integer startPage;//从第几条开始查询 [ 分页查询需要的参数 ]
    private Integer pageSize; //每页显示的条数 [ 分页查询需要的参数 ]
    private Integer blogId;//博客id
    private Integer labelId;//标签id
    private Integer typeId;//分类id
    private Integer commentId;//评论id
    private Integer publishYear;//发布年份
    private Integer publishMonth;//发布月份
    private Integer readingQuantity; //阅读量
    private Integer likeNumInt; //点赞量
    private Boolean waitForReply;//待回复
    private Boolean openHomeRecommend; //首页推荐
    private Boolean openCopyright; //版权信息
    private Boolean openComment; //评论功能
    private Boolean status = true; //状态

    @Override
    public String toString() {
        return "SpongeTermDO{" +
                "startPage=" + startPage +
                ", pageSize=" + pageSize +
                ", blogId=" + blogId +
                ", labelId=" + labelId +
                ", typeId=" + typeId +
                ", commentId=" + commentId +
                ", publishYear=" + publishYear +
                ", publishMonth=" + publishMonth +
                ", readingQuantity=" + readingQuantity +
                ", likeNumInt=" + likeNumInt +
                ", waitForReply=" + waitForReply +
                ", openHomeRecommend=" + openHomeRecommend +
                ", openCopyright=" + openCopyright +
                ", openComment=" + openComment +
                ", status=" + status +
                '}';
    }

    public static SpongeTermDO getInstance(){
        return spongeTermDO;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Boolean getWaitForReply() {
        return waitForReply;
    }

    public void setWaitForReply(Boolean waitForReply) {
        this.waitForReply = waitForReply;
    }

    public Boolean getOpenHomeRecommend() {
        return openHomeRecommend;
    }

    public void setOpenHomeRecommend(Boolean openHomeRecommend) {
        this.openHomeRecommend = openHomeRecommend;
    }

    public Boolean getOpenCopyright() {
        return openCopyright;
    }

    public void setOpenCopyright(Boolean openCopyright) {
        this.openCopyright = openCopyright;
    }

    public Boolean getOpenComment() {
        return openComment;
    }

    public void setOpenComment(Boolean openComment) {
        this.openComment = openComment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
