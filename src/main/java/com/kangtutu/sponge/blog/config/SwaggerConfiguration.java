package com.kangtutu.sponge.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kangtutu.sponge.blog.controller"))//配置需要扫描的接口包名
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        //定义联系人信息
        Contact contact = new Contact("kangtutu","www.sponge-k.tech","sponge@sponge.com");
        return new ApiInfoBuilder()
                .title("Sponge-k 个人技术博客")
                .description("测试")
                .termsOfServiceUrl("http://www.sponge-k.tech")
                .version("项目版本:1.0")
                .contact(contact)
                .build();
    }

}

