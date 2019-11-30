package com.kangtutu.sponge.blog.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ParameterCalibrationException extends RuntimeException {

    private Integer code;
    private String message;
    private String url;

    public ParameterCalibrationException() {
    }

    public ParameterCalibrationException(Integer code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ParameterCalibrationException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
