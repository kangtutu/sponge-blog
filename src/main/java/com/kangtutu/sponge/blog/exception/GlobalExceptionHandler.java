package com.kangtutu.sponge.blog.exception;

import com.kangtutu.sponge.blog.exception.custom.ParameterCalibrationException;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //顶级异常处理方法
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.OK)
    public ModelAndView exception(HttpServletRequest request,Exception e){
        log.info("进入全局异常处理方法内");
        //创建返回对象
        ModelAndView modelAndView = new ModelAndView();
        //将错误信息进行友好性提示
        modelAndView.addObject("code",e);
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("message",e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
