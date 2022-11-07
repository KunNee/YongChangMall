package com.YongChang.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.Contants;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.Result;
import com.YongChang.model.CustomerAddressEntity;
import com.YongChang.model.CustomerEntity;
import com.YongChang.service.CustomerAddressService;
import com.YongChang.table.CustomerAddressTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("customerAddress")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService service;


    @RequestMapping("list.do")
    public String list(Model model)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(CustomerAddressTable.CUSTOMER_ID,customerEntity.getId());
        List<CustomerAddressEntity> list = service.selectList(wrapper);
        model.addAttribute("list",list);
        return "mobile/address_list";
    }



    @RequestMapping("save.do")
    public String save(Model model,String id)throws Exception{
        CustomerAddressEntity entity = new CustomerAddressEntity();
        if(!StringUtils.isEmpty(id)){
            entity = service.selectById(id);
        }
        model.addAttribute("entity",entity);
        return "mobile/address_save";
    }


    @RequestMapping("saveData.do")
    @ResponseBody
    public Result save(Model model, CustomerAddressEntity entity)throws Exception{
        CustomerEntity customerEntity = Contants.getCustomer();
        entity.setCustomerId(customerEntity.getId());
        if(StringUtils.isEmpty(entity.getId())){
            entity.setId(IdWorkerUtil.getId());
            service.insert(entity);
        }else{
            service.updateById(entity);
        }
        return Result.success("保存成功");
    }


    @PostMapping("del.do")
    @ResponseBody
    public Result del(String id)throws Exception{
        service.deleteById(id);
        return Result.success("保存成功");
    }
}
