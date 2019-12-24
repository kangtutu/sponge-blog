package com.kangtutu.sponge.blog.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

/**
 * 用于处理用户登陆时权限不足的友好提示
 */
@Configuration
@Slf4j
public class SecurityErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //使用官方提供的枚举类进行状态码获取 - 该状态需要跳转的页面
        //此处可以添加多个状态码对应的跳转页面
        ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN,"/error");
        //将参数传入，此处可以传入多个定义的状态码跳转连接
        registry.addErrorPages(error403);
    }
}
