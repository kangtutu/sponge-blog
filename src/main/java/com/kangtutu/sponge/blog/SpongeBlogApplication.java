package com.kangtutu.sponge.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.kangtutu.sponge.blog.mapper")
public class SpongeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpongeBlogApplication.class,args);
    }
}
