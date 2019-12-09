package com.kangtutu.sponge.blog.pojo.dto;

import java.io.Serializable;

//服务层返回对象
public class ResultObjectDTO implements Serializable {
    private Integer code;
    private String  message;
    private Boolean status; //true-调用成功，false-调用失败
    private Object data;

    public ResultObjectDTO() {
    }

    public static ResultObjectDTO success(){
        return new ResultObjectDTO(200,null,true,null);
    }

    public static ResultObjectDTO success(Object obj){
        return new ResultObjectDTO(200,null,true,obj);
    }

    public static ResultObjectDTO failure(){
        return new ResultObjectDTO(500,null,false,null);
    }

    public static ResultObjectDTO failure(Object obj){
        return new ResultObjectDTO(500,null,false,obj);
    }

    public ResultObjectDTO(Integer code, String message, Boolean status, Object data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultObjectDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", status=" + status +
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
