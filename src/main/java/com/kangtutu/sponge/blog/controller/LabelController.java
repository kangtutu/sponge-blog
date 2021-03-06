package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.service.LabelService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeLimitVO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("/blog/label")
@Slf4j
@Api(tags = "[前台] 标签相关接口")
public class LabelController {

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

    @Autowired
    private BlogService blogService;

    @Autowired
    private LabelService labelService;

    //标签首页
    @GetMapping
    @ApiOperation("标签文章列表首页数据接口")
    @ApiResponses({
            @ApiResponse(code=200,message = "查询成功"),
            @ApiResponse(code=500,message = "系统异常")
    })
    public String labelIndex(Model model){
        log.info("[标签首页] 进入方法内");
        //查询数据库中启用的标签数据
        ResultObjectDTO labelDTO = labelService.getLabelAll(true);
        log.info("[标签首页] 查询到的已经启用的标签数据为:{}",labelDTO);
        //按照查询到的第一个标签数据进行数据展示
        SpongeTermDO term = new SpongeTermDO();
        term.setStartPage(0);
        term.setPageSize(pageSize);
        log.info("[标签首页] 用于查询文章列表的条件对象为:{}",term);
        //封装分页返回对象
        SpongeLimitVO limit = setLimitParam(term,1,18989);
        log.info("[标签首页] 返回页面的分页数据对象数据为:{}",limit);
        model.addAttribute("labels",labelDTO.getData());
        model.addAttribute("blogLimit",limit);
        return "labelList";
    }

    /**
     * 分页查询指定id数据
     * @param startPage
     * @return
     */
    @GetMapping("/page/{labelId}/{startPage}")
    @ResponseBody
    @ApiOperation("标签分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "labelId",dataType = "Integer",required = true,value = "标签id"),
            @ApiImplicitParam(paramType = "query",name = "startPage",dataType = "Integer",required = true,value = "起始页数")
    })
    @ApiResponses({
            @ApiResponse(code=200,message = "查询成功"),
            @ApiResponse(code=500,message = "系统异常")
    })
    public SpongeResultVO blogLimit(@PathVariable("labelId") Integer labelId, @PathVariable("startPage") Integer startPage){
        log.info("[标签分页查询] 进入分页查询方法内，传入大类id:{},分页起始值:{}",labelId,startPage);
        SpongeTermDO term = SpongeTermDO.getInstance();
        Integer start = (startPage-1)*pageSize;
        term.setStartPage(start);
        term.setPageSize(pageSize);
        if(labelId != 18989){
            term.setLabelId(labelId);
        }
        log.info("[标签分页查询] 封装的查询条件对象为:{}",term);
        SpongeLimitVO spongeLimitVO = setLimitParam(term, startPage,labelId);
        log.info("[标签分页查询] 返回页面数据响应对象数据为:{}",spongeLimitVO);
        return SpongeResultVO.success(spongeLimitVO);
    }


    //封装分页数据对象
    private SpongeLimitVO setLimitParam(SpongeTermDO term,Integer startPage,Integer objId){
        log.info("封装分页参数方法内,条件对象:{},分页起始数{},大类id:{}",term,startPage,objId);
        ResultObjectDTO blogDTO = blogService.getBlogByLabelOrTypeByTerm(term);
        //查询全量数据条数
        Integer count = (Integer) blogService.getCountByTerm(term).getData();
        //封装分页返回对象
        SpongeLimitVO limit = new SpongeLimitVO();
        limit.setPageSize(pageSize);
        //计算总页数
        int pageCount = new BigDecimal(count).divide(new BigDecimal(pageSize),0,BigDecimal.ROUND_UP).intValue();
        limit.setPageCount(pageCount);//总页数
        limit.setCurrentPageNumber(startPage);//当前页数
        if(pageCount<=startPage){
            limit.setLastPage(true);
        }else{
            limit.setLastPage(false);
        }
        limit.setData(blogDTO.getData());
        limit.setObjId(objId);
        return limit;
    }

}
