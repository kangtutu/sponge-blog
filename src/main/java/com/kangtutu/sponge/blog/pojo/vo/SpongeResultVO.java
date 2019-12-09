package com.kangtutu.sponge.blog.pojo.vo;

import java.io.Serializable;

/**
 * 用于封装返渲染页面数据的对象类
 */
public class SpongeResultVO implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    private SpongeResultVO() {}

    public static SpongeResultVO success(){
        return new SpongeResultVO(ResultCodeEnumVO.SUCCESS.getCode(),ResultCodeEnumVO.SUCCESS.getMessage(),null);
    }

    public static SpongeResultVO success(Object data){
        return new SpongeResultVO(ResultCodeEnumVO.SUCCESS.getCode(),null,data);
    }

    public static SpongeResultVO failure(){
        return new SpongeResultVO(ResultCodeEnumVO.FAILURE.getCode(),ResultCodeEnumVO.FAILURE.getMessage(),null);
    }

    public static SpongeResultVO failure(Object data){
        return new SpongeResultVO(ResultCodeEnumVO.FAILURE.getCode(),null,data);
    }

    public SpongeResultVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "SpongeResultVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
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
