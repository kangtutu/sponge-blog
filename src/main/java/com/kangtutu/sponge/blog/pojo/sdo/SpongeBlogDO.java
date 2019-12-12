package com.kangtutu.sponge.blog.pojo.sdo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SpongeBlogDO implements Serializable {
    private Integer blogId;
    private String author; //作者
    private String title; //标题
    private String content; //内容-解析成html格式的
    private String markdownContent;//内容-未作任何解析的，直接放入编辑器中进行解析修改内容即可
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
    private Boolean openHomeRecommend; //首页推荐
    private Boolean openCopyright; //版权信息
    private Boolean openComment; //评论功能
    private List<SpongeCommentDO> comments; //评论信息
    private Boolean status; //状态 0-启动 1-禁用 2-停用
    private String creationUser; //创建者
    private Date creationTime; //创建时间
    private String updateUser; //更新者
    private Date updateTime; //更新时间

    @Override
    public String toString() {
        return "SpongeBlogDO{" +
                "blogId=" + blogId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", markdownContent='" + markdownContent + '\'' +
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
                ", openHomeRecommend=" + openHomeRecommend +
                ", openCopyright=" + openCopyright +
                ", openComment=" + openComment +
                ", comments=" + comments +
                ", status=" + status +
                ", creationUser='" + creationUser + '\'' +
                ", creationTime=" + creationTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

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

    public Integer getBlogNature() {
        return blogNature;
    }

    public void setBlogNature(Integer blogNature) {
        this.blogNature = blogNature;
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

    public List<SpongeCommentDO> getComments() {
        return comments;
    }

    public void setComments(List<SpongeCommentDO> comments) {
        this.comments = comments;
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

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }
}
