package com.kangtutu.sponge.blog.pojo;

import java.io.Serializable;

public class SKLimitResultVO implements Serializable {

    private Integer pageSize;//每页显示条数
    private Integer totalNumber;//总条数
    private Integer pageIndex;//当前页数
    private Boolean lastPage = true;//是否存在末页
    private Object data;//数据体

    @Override
    public String toString() {
        return "SKLimitResultVO{" +
                "pageSize=" + pageSize +
                ", totalNumber=" + totalNumber +
                ", pageIndex=" + pageIndex +
                ", lastPage=" + lastPage +
                ", data=" + data +
                '}';
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }
}
