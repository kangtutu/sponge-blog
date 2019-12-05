package com.kangtutu.sponge.blog.controller;

import com.kangtutu.sponge.blog.Service.BlogService;
import com.kangtutu.sponge.blog.Service.LabelService;
import com.kangtutu.sponge.blog.pojo.SKBlog;
import com.kangtutu.sponge.blog.pojo.SKLabel;
import com.kangtutu.sponge.blog.pojo.SKLimitResultVO;
import com.kangtutu.sponge.blog.pojo.SKTerm;
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
@RequestMapping("/blog/label")
public class LabelController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private LabelService labelService;

    //分页查询每页显示条数
    @Value("${blog.limit.page-size}")
    private Integer pageSize;

    /**
     * 标签首页连接
     * 1. 首次进入标签页查询出来的列表是按照发布时间排序分页查询出来的数据
     * 1.1 当传入的objId值为926999时表示从全量的博客列表数据中查询不区分标签
     * 2. 查询数据库标签表中启用的标签数据
     * @param model
     * @return
     */
    @GetMapping
    public String label(Model model){
        SKTerm skTerm = new SKTerm();
        skTerm.setPageSize(pageSize);
        skTerm.setTopCurrPage(0);
        //查询所有标签列表
        List<SKBlog> blogList = blogService.getBlogByTerm(skTerm);
        SKLimitResultVO skLimitResultVO = setSKLimitResultVO(blogList,926999, 1);
        //查询出所有的标签数据
        List<SKLabel> labelList = labelService.getLabelByStatus(true);
        model.addAttribute("blogLimit",skLimitResultVO);
        model.addAttribute("labelList",labelList);
        return "labelList";
    }

    /**
     * 分页查询指定标签id的数据
     * @param objId 926999-按照全量数据进行分页
     * @param currPage 当前查询第几页数据
     * @return
     */
    @GetMapping("/page/{objId}/{currPage}")
    @ResponseBody
    public SKLimitResultVO getLimitBlog(@PathVariable("objId") Integer objId,@PathVariable("currPage") Integer currPage){
        SKTerm skTerm = new SKTerm();
        if(objId != 926999){
            skTerm.setLabelId(objId);
        }
        skTerm.setPageSize(pageSize);
        Integer num = (currPage-1)*pageSize;
        skTerm.setTopCurrPage(num);
        List<SKBlog> blogByTerm = blogService.getBlogByTerm(skTerm);
        SKLimitResultVO skLimitResultVO = setSKLimitResultVO(blogByTerm, objId,currPage);
        return skLimitResultVO;
    }

    //封装分页数据查询
    private SKLimitResultVO setSKLimitResultVO(List<SKBlog> blogList,Integer objId,Integer topCurrPage){
        //查询总条数
        Integer total = blogService.getBlogTotal();
        SKLimitResultVO skLimitResultVO = new SKLimitResultVO();
        skLimitResultVO.setPageSize(pageSize);
        //计算总页数
        int pageTotal = new BigDecimal(total).divide(new BigDecimal(pageSize),0,BigDecimal.ROUND_UP).intValue();
        skLimitResultVO.setTotalNumber(pageTotal);//总页数
        skLimitResultVO.setPageIndex(topCurrPage);//当前页数
        skLimitResultVO.setData(blogList);
        skLimitResultVO.setObjId(objId);
        if(topCurrPage>=pageTotal){
            skLimitResultVO.setLastPage(false);
        }
        return skLimitResultVO;
    }

}
