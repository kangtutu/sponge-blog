package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.Service.TypeService;
import com.kangtutu.sponge.blog.pojo.dto.ResultObjectDTO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTermDO;
import com.kangtutu.sponge.blog.pojo.sdo.SpongeTypeDO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeLimitVO;
import com.kangtutu.sponge.blog.pojo.vo.SpongeResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/blog/type")
public class TypeController {

    private static final Logger log = LoggerFactory.getLogger(TypeController.class);

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    //分类首页
    @GetMapping
    public String type(Model model){
        //查询数据库中启用的标签数据
        ResultObjectDTO typeDTO = typeService.getTypeAll(true);

        //按照查询到的第一个标签数据进行数据展示
        //List<SpongeTypeDO> types = (List<SpongeTypeDO>) typeDTO.getData();
        SpongeTermDO term = SpongeTermDO.getInstance();
        term.setStartPage(0);
        term.setPageSize(pageSize);
        SpongeLimitVO blogLimit = setLimitParam(term, 1);

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
        SpongeTermDO term = SpongeTermDO.getInstance();
        Integer start = (startPage-1)*pageSize;
        term.setStartPage(start);
        term.setPageSize(pageSize);
        term.setTypeId(typeId);
        SpongeLimitVO spongeLimitVO = setLimitParam(term, startPage);
        return SpongeResultVO.success(spongeLimitVO);
    }


    //封装分页数据对象
    private SpongeLimitVO setLimitParam(SpongeTermDO term,Integer startPage){
        ResultObjectDTO blogDTO = blogService.getBlogByLabelOrTypeByTerm(term);
        //查询全量数据条数
        Integer count = (Integer) blogService.getCountByTerm(term).getData();
        //封装分页返回对象
        SpongeLimitVO limit = new SpongeLimitVO();
        limit.setPageSize(pageSize);
        int pageCount = new BigDecimal(count).divide(new BigDecimal(pageSize),0,BigDecimal.ROUND_UP).intValue();
        limit.setPageCount(pageCount);//总页数
        limit.setCurrentPageNumber(startPage);//当前页数
        if(count<=startPage){
            limit.setLastPage(true);
        }else{
            limit.setLastPage(false);
        }
        limit.setData(blogDTO.getData());
        return limit;
    }

}
