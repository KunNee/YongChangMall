package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.PageVo;
import com.YongChang.config.Result;
import com.YongChang.model.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ChildTypeService childTypeService;

    @Autowired
    private ArticleShopService articleShopService;

    @Autowired
    private ShopService shopService;






    @GetMapping("list.htm")
    public String list(Model model)throws Exception{
        return "article/list";
    }



    @GetMapping("page.htm")
    @ResponseBody
    public PageVo page(String title, String  summary,int page , int limit)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        if(!StringUtils.isEmpty(title)){
            entityWrapper.like(ArticleTable.TITLE,title);
        }
        if(!StringUtils.isEmpty(summary)){
            entityWrapper.like(ArticleTable.SUMMARY,summary);
        }
        entityWrapper.orderBy("top",false).orderBy("time",false);
        Page<ArticleEntity> paged = new Page();
        paged.setSize(limit);
        paged.setCurrent(page);
        Page<ArticleEntity> userTablePage = articleService.selectPage(paged, entityWrapper);

        List<ArticleEntity> list = userTablePage.getRecords();
        if(list!=null && !list.isEmpty()){
            for (ArticleEntity articleEntity : list) {
                CustomerEntity customerEntity = customerService.selectById(articleEntity.getCustomerId());
                if(customerEntity!=null){
                    articleEntity.setCustomerName(customerEntity.getName());
                }
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


    @GetMapping("add.htm")
    public String add(Model model)throws Exception{
        List<ShopEntity> shopEntities =  shopService.selectList(new EntityWrapper<>());
        model.addAttribute("shopEntities",shopEntities);
        return "article/add";
    }



    @RequestMapping("addData.htm")
    @ResponseBody
    public Result addDatax(ArticleEntity articleEntity, HttpSession session)throws Exception{
        articleEntity.setId(IdWorkerUtil.getId());
        articleEntity.setTime(new Date());
        articleEntity.setStatus(1);
        articleEntity.setCustomerId("-1");
        articleService.insert(articleEntity);
        return Result.success("保存成功");
    }



    @RequestMapping("shopList.html")
    @ResponseBody
    public Result shopList(String id)throws Exception{
        ArticleEntity articleEntity = articleService.selectById(id);
        if(StringUtils.isEmpty(articleEntity.getLabel())){
            return Result.success("无");
        }
        String[] lebel = articleEntity.getLabel().split(",");
        //查询
        List<String> ids = new ArrayList<>();
        for (String s : lebel) {
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.like("label",s);
            List<ShopEntity> shops = shopService.selectList(wrapper);
            if(shops!=null){
                for (ShopEntity shop : shops) {
                    ids.add(shop.getId());
                }
            }
        }
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.in("id",ids).orderBy("rand()");
        List<ShopEntity> shops = shopService.selectList(wrapper);
        if(shops!=null){
            if(shops.size()>3){
                shops = shops.subList(0,3);
            }
        }
        return Result.success(shops);
    }



    @GetMapping("info.html")
    public String info(String id, Model model)throws Exception{
        ArticleEntity articleEntity = articleService.selectById(id);
        model.addAttribute("article",articleEntity);
        model.addAttribute("id",id);
        String[]  lebel = articleEntity.getLabel().split(",");
        model.addAttribute("lebels",lebel);
        CustomerEntity customerEntity = customerService.selectById(articleEntity.getCustomerId());
        model.addAttribute("customerEntity",customerEntity);
        if(!StringUtils.isEmpty(articleEntity.getShopIds())){
            String articles[] = articleEntity.getShopIds().split(",");
            List<String> str = new ArrayList<>();
            for (String article : articles) {
                str.add(article);
            }
            List<ShopEntity> shopEntities = shopService.selectBatchIds(str);
            model.addAttribute("shopEntities",shopEntities);
        }
        return "article/info";
    }



    @GetMapping("edit.htm")
    public String edit(String id, Model model )throws Exception{
        ArticleEntity articleEntity = articleService.selectById(id);
        model.addAttribute("articleEntity",articleEntity);
        List<ShopEntity> shopEntities =  shopService.selectList(new EntityWrapper<>());
        model.addAttribute("shopEntities",shopEntities);
        return "article/edit";
    }


    @RequestMapping("editData.htm")
    @ResponseBody
    public Result editData(ArticleEntity articleEntity)throws Exception{
        articleService.updateById(articleEntity);
        return Result.success("保存成功");
    }


}
