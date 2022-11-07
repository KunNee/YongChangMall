package com.YongChang.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.Result;
import com.YongChang.mapper.OrderShopDao;
import com.YongChang.model.ArticleEntity;
import com.YongChang.model.OrderShopEntity;
import com.YongChang.model.ShopEntity;
import com.YongChang.service.ArticleService;
import com.YongChang.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("mobileShop")
public class MobileShopController {
    @Autowired
    private ShopService shopService;


    @Autowired
    private OrderShopDao orderShopDao;

    @Autowired
    private ArticleService articleService;


    @RequestMapping("info.html")
    public String info(String id,String customerId, Model model)throws Exception{
        ShopEntity shopEntity =  shopService.selectById(id);
        model.addAttribute("shop",shopEntity);
        model.addAttribute("id",id);
        List<OrderShopEntity> list = orderShopDao.list(id);
        model.addAttribute("list",list);
        model.addAttribute("customerId",customerId);
        return "mobile/shop_info";
    }
    @RequestMapping("aList.html")
    @ResponseBody
    public Result shopList(String id)throws Exception{
        ShopEntity shopEntity =  shopService.selectById(id);
        if(StringUtils.isEmpty(shopEntity.getLabel())){
            return Result.success("无");
        }
        String[] lebel = shopEntity.getLabel().split(",");
        //查询
        List<String> ids = new ArrayList<>();
        for (String s : lebel) {
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.like("label",s);
            List<ArticleEntity> shops = articleService.selectList(wrapper);
            if(shops!=null){
                for (ArticleEntity shop : shops) {
                    ids.add(shop.getId());
                }
            }
        }
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.in("id",ids).orderBy("rand()");
        List<ArticleEntity> shops = articleService.selectList(wrapper);
        if(shops!=null){
            if(shops.size()>3){
                shops = shops.subList(0,3);
            }
        }

        return Result.success(shops);
    }


}
