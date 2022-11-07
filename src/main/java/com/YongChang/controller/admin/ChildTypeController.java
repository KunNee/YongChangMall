package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.YongChang.config.PageVo;
import com.YongChang.config.Result;
import com.YongChang.model.ChildTypeEntity;
import com.YongChang.service.ChildTypeService;
import com.YongChang.table.ChildTypeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("childType")
public class ChildTypeController {

    @Autowired
    private ChildTypeService childTypeService  ;


    @PostMapping("childList.htm")
    @ResponseBody
    public Result childList(Integer type){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<ChildTypeEntity> list =  childTypeService.selectList(entityWrapper);
        return Result.success(list);
    }


    @PostMapping("childList.do")
    @ResponseBody
    public Result childListdo(Integer type){
        EntityWrapper entityWrapper = new EntityWrapper();
        List<ChildTypeEntity> list =  childTypeService.selectList(entityWrapper);
        return Result.success(list);
    }



    @GetMapping("indexPage.htm")
    public String indexPage(Model model)throws Exception{
       return "childType/list";
    }



    @GetMapping("page.htm")
    @ResponseBody
    public PageVo indexPage(Model model, String name, int page , int limit)throws Exception{

        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.like(ChildTypeTable.NAME,name);


        Page<ChildTypeEntity> paged = new Page();
        paged.setSize(limit);
        paged.setCurrent(page);
        Page<ChildTypeEntity> userTablePage = childTypeService.selectPage(paged, entityWrapper);

        PageVo<ChildTypeEntity> pageVo = new PageVo<>();
        pageVo.setCode(0);
        pageVo.setCount(paged.getTotal());
        pageVo.setData(userTablePage.getRecords());
        pageVo.setPageNum(limit);
        pageVo.setPageSize(page);
        return pageVo;

    }


    @GetMapping("savePage.htm")
    public String savePage(Model model){
        return  "childType/save";
    }


    @PostMapping("save.htm")
    @ResponseBody
    public Result savePage(Model model , ChildTypeEntity entity){
        if(entity.getId()==null || entity.getId().equals("")){
            entity.setId(IdWorker.get32UUID());
            childTypeService.insert(entity);
        }
        return Result.success("用户保存成功");
    }



}
