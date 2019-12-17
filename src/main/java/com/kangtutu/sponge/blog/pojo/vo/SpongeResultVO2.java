package com.kangtutu.sponge.blog.pojo.vo;

import java.io.Serializable;

/**
 * 用于封装返渲染页面数据的对象类
 */
public class SpongeResultVO2 implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    private SpongeResultVO2() {}

    public SpongeResultVO2 getResult(){
        return new SpongeResultVO2();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
