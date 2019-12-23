package com.kangtutu.sponge.blog.controller.background;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 综合控制类
 */
@Controller
@RequestMapping("/bg/comprehensive")
@Slf4j
public class BackgroundComprehensiveController {

    @GetMapping("/swagger")
    public String swagger(){
        log.info("[SwaggerAPI] 跳转到SwaggerAPI");
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/druid")
    public String druid(){
        log.info("[Druid SQL] 跳转到Druid SQL");
        return "redirect:/druid";
    }
}
