package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import com.kangtutu.sponge.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//后台文章操作
@Controller
@RequestMapping("/blog/background/type")
public class BackgroundTypeController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundTypeController.class);

   @GetMapping
    public String type(){
       log.info("[后台分类]");
       //查询所有分类
       return "";
   }

}
