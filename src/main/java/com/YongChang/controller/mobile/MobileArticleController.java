package com.YongChang.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.YongChang.config.Contants;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.PageVo;
import com.YongChang.config.Result;
import com.YongChang.model.ArticleEntity;
import com.YongChang.model.ChildTypeEntity;
import com.YongChang.model.ShopEntity;
import com.YongChang.service.*;
import com.YongChang.table.ArticleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("article")
public class MobileArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ChildTypeService childTypeService;

    @Autowired
    private ShopService shopService;



    @GetMapping("list.do")
    public String list(Model model)throws Exception{
        return "mobile/article/list";
    }



    @GetMapping("page.do")
    @ResponseBody
    public PageVo page(String title, String  summary,int page , int limit)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        if(!StringUtils.isEmpty(title)){
            entityWrapper.like(ArticleTable.TITLE,title);
        }
        if(!StringUtils.isEmpty(summary)){
            entityWrapper.like(ArticleTable.SUMMARY,summary);
        }
        entityWrapper.eq(ArticleTable.CUSTOMER_ID, Contants.getCustomer().getId());
        entityWrapper.orderBy("time",false);
        Page<ArticleEntity> paged = new Page();
        paged.setSize(limit);
        paged.setCurrent(page);
        Page<ArticleEntity> userTablePage = articleService.selectPage(paged, entityWrapper);

        List<ArticleEntity> list = userTablePage.getRecords();
        if(list!=null && !list.isEmpty()){
            for (ArticleEntity articleEntity : list) {
                ChildTypeEntity childTypeEntity = childTypeService.selectById(articleEntity.getChildType());
                if(childTypeEntity!=null){
                    articleEntity.setChildName(childTypeEntity.getName());
                }
            }
        }
        PageVo<ArticleEntity> pageVo = new PageVo<>();
        pageVo.setCode(0);
        pageVo.setCount(paged.getTotal());
        pageVo.setData(list);
        pageVo.setPageNum(limit);
        pageVo.setPageSize(page);
        return pageVo;

    }


    @GetMapping("add.do")
    public String add(Model model)throws Exception{
        List<ShopEntity> shopEntities =  shopService.selectList(new EntityWrapper<>());
        model.addAttribute("shopEntities",shopEntities);
        return "mobile/article/add";
    }



    @RequestMapping("addData.do")
    @ResponseBody
    public Result addDatax(ArticleEntity articleEntity, HttpSession session)throws Exception{
        articleEntity.setId(IdWorkerUtil.getId());
        articleEntity.setTime(new Date());
        articleEntity.setStatus(0);
        articleEntity.setCustomerId(Contants.getCustomer().getId());
        articleEntity.setTop(false);
        articleService.insert(articleEntity);
        return Result.success("保存成功");
    }



    @GetMapping("edit.do")
    public String edit(String id, Model model )throws Exception{
        ArticleEntity articleEntity = articleService.selectById(id);
        model.addAttribute("articleEntity",articleEntity);
        List<ShopEntity> shopEntities =  shopService.selectList(new EntityWrapper<>());
        model.addAttribute("shopEntities",shopEntities);
        return "mobile/article/edit";
    }

    @RequestMapping("editData.do")
    @ResponseBody
    public Result editData(ArticleEntity articleEntity)throws Exception{
        articleService.updateById(articleEntity);
        return Result.success("保存成功");
    }


}
