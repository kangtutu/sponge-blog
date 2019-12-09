package com.kangtutu.sponge.blog.pojo.vo;

import java.io.Serializable;

public class SpongeLimitVO implements Serializable {

    private Integer pageCount; //总页数
    private Integer pageSize; //每页显示条数
    private Integer currentPageNumber; //当前页数
    private Boolean lastPage; //是否为末页 true-末页 false-不是末页
    private Object data; //分页数据

    public SpongeLimitVO() {
    }

    public SpongeLimitVO(Integer pageCount, Integer pageSize, Integer currentPageNumber, Boolean lastPage, Object data) {
        this.pageCount = pageCount;
        this.pageSize = pageSize;
        this.currentPageNumber = currentPageNumber;
        this.lastPage = lastPage;
        this.data = data;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(Integer currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SpongeLimitVO{" +
                "pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", currentPageNumber=" + currentPageNumber +
                ", lastPage=" + lastPage +
                ", data=" + data +
                '}';
    }
}
