package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeArchiveVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 归档数据
 */
@Controller
@RequestMapping("/blog/archive")
@Slf4j
@Api(tags = "[前台] 归档信息相关接口")
public class ArchiveController {

    @Autowired
    private BlogService blogService;

    //归档首页
    @GetMapping
    public String archiveIndex(Model model){
        log.info("[文章归档] 进入归档首页方法内");
        //查询数据库表中存在的年份
        ResultObjectDTO blogPublishYear = blogService.getBlogPublishYear();
        log.info("[文章归档] 查询数据库表中存在的年份,{}",blogPublishYear);
        //创建年份归档数据对象
        List<SpongeArchiveVO> archives = new ArrayList<>();
        //按照月份查询对应归档数据
        List<Integer> years = (List<Integer>) blogPublishYear.getData();
        SpongeTermDO term = new SpongeTermDO();
        for(int i=0;i<years.size();i++){
            SpongeArchiveVO archive = new SpongeArchiveVO();
            //获取本次需要查询的年份
            Integer year = years.get(i);
            //封装页面数据对象参数
            archive.setYear(year);
            //封装查询条件参数
            term.setPublishYear(year);
            //查询指定年份数据
            ResultObjectDTO blogByTerm = blogService.getBlogByTerm(term);
            //封装页面数据对象参数
            archive.setData(blogByTerm.getData());
            archives.add(archive);
        }
        log.info("[文章归档] 封装页面返回数据:{}",archives);
        model.addAttribute("archives",archives);
        return "archive";
    }

}
