package com.kangtutu.sponge.blog.controller.background;

import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeLabelDO;
import com.kangtutu.sponge.blog.service.LabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//后台文章操作
@Controller
@RequestMapping("/bg/label")
public class BackgroundLabelController {

    private static final Logger log = LoggerFactory.getLogger(BackgroundLabelController.class);

    @Autowired
    private LabelService labelService;

   @GetMapping
    public String label(Model model){
       log.info("[后台标签]");
       ResultObjectDTO result = labelService.getLabelAll(null);
       if(result.getCode() != 200){
           model.addAttribute("code",500);
           model.addAttribute("message","查询文章列表数据失败");
           return "error";
       }
       model.addAttribute("labels",result.getData());
       return "background/label";
   }

   @PostMapping("/add")
    public String addLabel(SpongeLabelDO spongeLabelDO){
       ResultObjectDTO result = labelService.saveLabel(spongeLabelDO);
       if(result.getCode() != 200){
           return "error";
       }
       return "redirect:/bg/label";
   }

    @GetMapping("/toUpdate/{labelId}")
    public String toUpdateLabel(@PathVariable("labelId")Integer labelId,Model model){
        ResultObjectDTO result = labelService.getLabelById(labelId);
        if(result.getCode() != 200){
            return "error";
        }
        model.addAttribute("label",result.getData());
        return "background/labelEditor";
    }

    @PostMapping("/update")
    public String updateLabel(SpongeLabelDO spongeLabelDO){
        ResultObjectDTO result = labelService.updateLabel(spongeLabelDO);
        if(result.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/label";
    }

    @GetMapping("/delete/{labelId}")
    public String deleteLabel(@PathVariable("labelId")Integer labelId){
        ResultObjectDTO result = labelService.deleteLabelById(labelId);
        if(result.getCode() != 200){
            return "error";
        }
        return "redirect:/bg/label";
    }

}
