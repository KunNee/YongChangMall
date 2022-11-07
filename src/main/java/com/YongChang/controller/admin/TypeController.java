package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.Result;
import com.YongChang.model.ShopTypeEntity;
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
@RequestMapping("shoptype")
public class TypeController {

    @Autowired
    private ShopTypeService service;

    @RequestMapping("list.htm")
    public String list(Model model){
        List<ShopTypeEntity> list = service.selectList(new EntityWrapper<>());
        model.addAttribute("list",list);
        return "shoptype/list";
    }



    @RequestMapping("save.htm")
    public String save(Model model,String id)throws Exception{
        ShopTypeEntity entity = new ShopTypeEntity();
        if(!StringUtils.isEmpty(id)){
            entity = service.selectById(id);
        }
        model.addAttribute("entity",entity);
        return "shoptype/save";
    }


    @RequestMapping("saveData.htm")
    @ResponseBody
    public Result save(Model model, ShopTypeEntity entity)throws Exception{
        if(StringUtils.isEmpty(entity.getId())){
            entity.setId(IdWorkerUtil.getId());
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

}
