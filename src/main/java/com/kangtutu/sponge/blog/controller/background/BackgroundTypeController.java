package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeBlogDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTypeDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//后台文章操作
@Controller
@RequestMapping("/bg/type")
public class BackgroundTypeController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundTypeController.class);

    @Autowired
    private TypeService typeService;

   @GetMapping
    public String type(Model model){
       log.info("[后台分类]");
       ResultObjectDTO result = typeService.getTypeAll(null);
       if(result.getCode() != 200){
           model.addAttribute("code",500);
           model.addAttribute("message","查询文章列表数据失败");
           return "error";
       }
       model.addAttribute("types",result.getData());
       return "background/type";
   }

    @PostMapping("/add")
    public String addType(SpongeTypeDO spongeTypeDO){
        ResultObjectDTO result = typeService.saveType(spongeTypeDO);
        if(result.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/type";
    }

    @GetMapping("/toUpdate/{typeId}")
    public String addType(@PathVariable("typeId")Integer typeId,Model model){
        ResultObjectDTO result = typeService.getTypeById(typeId);
        if(result.getCode() != 200){
            return "error";
        }
        model.addAttribute("type",result.getData());
        return "background/typeEditor";
    }

    @PostMapping("/update")
    public String updateType(SpongeTypeDO spongeTypeDO){
        ResultObjectDTO result = typeService.updateType(spongeTypeDO);
        if(result.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/type";
    }

    @GetMapping("/delete/{typeId}")
    public String deleteType(@PathVariable("typeId")Integer typeId){
        ResultObjectDTO result = typeService.deleteTypeById(typeId);
        if(result.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/type";
    }

}
