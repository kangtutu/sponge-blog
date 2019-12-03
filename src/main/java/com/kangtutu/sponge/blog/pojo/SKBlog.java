package com.kangtutu.sponge.blog.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 博客类
 * @Create 海绵之家 - [ www.sponge-k.tech ]
 **/
public class SKBlog implements Serializable {

    private Integer blogId;
    private String author; //作者
    private String title; //标题
    private String content; //内容
    private String imageUrl; //首图地址
    private String labelName;//标签名称
    private Integer publishYear;//发布年份
    private Integer publishMonth;//发布月份
    private Integer labelId;//标签ID
    private Integer typeId;//类型ID
    private String typeName;//类型名称
    private Integer blogNature;//博客性质 1-原创 2-转载 3-其他
    private Integer readingQuantity; //阅读量
    private Integer likeNumInt; //点赞量
    private Boolean isOpenHomeRecommend; //首页推荐
    private Boolean isOpenCopyright; //版权信息
    private Boolean isOpenComment; //评论功能
    private List<SKComment> comments; //评论信息
    private Boolean status; //状态 0-启动 1-禁用 2-停用
    private String creationUser; //创建者
    private Date creationTime; //创建时间
    private String updateUser; //更新者
    private Date updateTime; //更新时间

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public List<SKComment> getComments() {
        return comments;
    }

    public void setComments(List<SKComment> comments) {
        this.comments = comments;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
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

    public Integer getBlogNature() {
        return blogNature;
    }

    public void setBlogNature(Integer blogNature) {
        this.blogNature = blogNature;
    }

    public Date getCreationTime() {
        return creationTime;
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

    @Override
    public String toString() {
        return "SKBlog{" +
                "blogId=" + blogId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", labelName='" + labelName + '\'' +
                ", publishYear=" + publishYear +
                ", publishMonth=" + publishMonth +
                ", labelId=" + labelId +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", blogNature=" + blogNature +
                ", readingQuantity=" + readingQuantity +
                ", likeNumInt=" + likeNumInt +
                ", isOpenHomeRecommend=" + isOpenHomeRecommend +
                ", isOpenCopyright=" + isOpenCopyright +
                ", isOpenComment=" + isOpenComment +
                ", comments=" + comments +
                ", status=" + status +
                ", creationUser='" + creationUser + '\'' +
                ", creationTime=" + creationTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}

