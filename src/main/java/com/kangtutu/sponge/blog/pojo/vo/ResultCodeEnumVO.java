package com.kangtutu.sponge.blog.pojo.vo;

/**
 * 自定义响应编码及对应的响应信息
 */
public enum  ResultCodeEnumVO {

    SUCCESS(200,"成功"),FAILURE(999,"失败");

    private Integer code;
    private String message;

    ResultCodeEnumVO(Integer code, String message) {
        this.code = code;
        this.message = message;
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
}
