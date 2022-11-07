package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.Result;
import com.YongChang.model.ShopEntity;
import com.YongChang.model.ShopTypeEntity;
import com.YongChang.service.ShopService;
import com.YongChang.service.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService service;

    @Autowired
    private ShopTypeService shopTypeService;



    @RequestMapping("list.htm")
    public String list(Model model){
        List<ShopEntity> list = service.selectList(new EntityWrapper<>());
        model.addAttribute("list",list);
        return "shop/list";
    }



    @RequestMapping("save.htm")
    public String save(Model model,String id)throws Exception{
        ShopEntity entity = new ShopEntity();
        entity.setStatus(true);
        entity.setHot(false);
        if(!StringUtils.isEmpty(id)){
            entity = service.selectById(id);
        }
        model.addAttribute("entity",entity);

        List<ShopTypeEntity> types =  shopTypeService.selectList(new EntityWrapper<>());
        model.addAttribute("types",types);
        return "shop/save";
    }


    @RequestMapping("saveData.htm")
    @ResponseBody
    public Result save(Model model, ShopEntity entity)throws Exception{
        if(StringUtils.isEmpty(entity.getId())){
            entity.setId(IdWorkerUtil.getId());
            entity.setStock(0);
            entity.setScore(0.0);
            service.insert(entity);
        }else{
            service.updateById(entity);
        }
        return Result.success("保存成功");
    }


    @PostMapping("del.htm")
    @ResponseBody
    public Result del(String id)throws Exception{
        service.deleteById(id);
        return Result.success("保存成功");
    }



    @RequestMapping("stock.htm")
    @ResponseBody
    public Result stock(Model model, Integer stock,String id ,Integer type)throws Exception{
        ShopEntity entity = service.selectById(id);
        if(entity.getStock()==null){
            entity.setStock(0);
        }
        if(type==1){ //添加库存
            entity.setStock(entity.getStock()+stock);
        }else{ //减少库存
            if(entity.getStock()-stock<0){
                return Result.error("库存不够");
            }
            entity.setStock(entity.getStock()-stock);
        }
        service.updateById(entity);
        return Result.success("保存成功");
    }

}
