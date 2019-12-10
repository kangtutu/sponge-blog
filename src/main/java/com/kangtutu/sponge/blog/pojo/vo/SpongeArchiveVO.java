package com.kangtutu.sponge.blog.pojo.vo;

import java.io.Serializable;

/**
 * 归档页面数据对象
 */
public class SpongeArchiveVO implements Serializable {

    private Integer year;
    private Integer month;
    private Object data;

    public SpongeArchiveVO() {
    }

    public SpongeArchiveVO(Integer year, Integer month, Object data) {
        this.year = year;
        this.month = month;
        this.data = data;
    }

    @Override
    public String toString() {
        return "SpongeArchiveVO{" +
                "year=" + year +
                ", month=" + month +
                ", data=" + data +
                '}';
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
