package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.service.BlogService;
import com.kangtutu.sponge.blog.service.TypeService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeLimitVO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import io.swagger.annotations.Api;
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
@RequestMapping("/blog/type")
@Slf4j
@Api(tags = "[前台] 分类相关接口")
public class TypeController {

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    //分类首页
    @GetMapping
    public String typeIndex(Model model){
        //查询数据库中启用的标签数据
        ResultObjectDTO typeDTO = typeService.getTypeAll(true);
        log.info("[分类首页] 查询到的已经启用的分类数据为:{}",typeDTO);
        //按照查询到的第一个标签数据进行数据展示
        SpongeTermDO term = SpongeTermDO.getInstance();
        term.setStartPage(0);
        term.setPageSize(pageSize);
        log.info("[分类首页] 用于查询文章列表的条件对象为:{}",term);
        SpongeLimitVO blogLimit = setLimitParam(term, 1,18989);
        log.info("[分类首页] 返回页面的分页数据对象数据为:{}",blogLimit);
        model.addAttribute("types",typeDTO.getData());
        model.addAttribute("blogLimit",blogLimit);
        return "typeList";
    }

    /**
     * 分页查询指定id数据
     * @param startPage
     * @return
     */
    @GetMapping("/page/{typeId}/{startPage}")
    @ResponseBody
    public SpongeResultVO blogLimit(@PathVariable("typeId") Integer typeId,@PathVariable("startPage") Integer startPage){
        log.info("[分类分页查询] 进入分页查询方法内，传入大类id:{},分页起始值:{}",typeId,startPage);
        SpongeTermDO term = SpongeTermDO.getInstance();
        Integer start = (startPage-1)*pageSize;
        term.setStartPage(start);
        term.setPageSize(pageSize);
        if(typeId != 18989){
            term.setTypeId(typeId);
        }
        log.info("[分类分页查询] 封装的查询条件对象为:{}",term);
        SpongeLimitVO spongeLimitVO = setLimitParam(term, startPage,typeId);
        log.info("[分类分页查询] 返回页面数据响应对象数据为:{}",spongeLimitVO);
        //return JSONObject.toJSONString(spongeLimitVO);
        return SpongeResultVO.success(spongeLimitVO);
    }


    //封装分页数据对象
    private SpongeLimitVO setLimitParam(SpongeTermDO term,Integer startPage,Integer objId){
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
