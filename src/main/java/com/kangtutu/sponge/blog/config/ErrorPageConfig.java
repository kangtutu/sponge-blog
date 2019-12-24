package com.kangtutu.sponge.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

/**
 * 用于处理用户登陆时权限不足的友好提示
 */
@Configuration
@Slf4j
public class ErrorPageConfig {

    //定义处理对应异常的页面
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                //403状态码标识权限不足
                factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/403"));
            }
        };
    }

}
