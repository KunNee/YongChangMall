package com.YongChang.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.Contants;
import com.YongChang.config.IdWorkerUtil;
import com.YongChang.config.Result;
import com.YongChang.model.CustomerEntity;
import com.YongChang.model.RoyaltyEntity;
import com.YongChang.service.CustomerService;
import com.YongChang.service.RoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("royalty")
public class RoyaltyController {

    @Autowired
    private RoyaltyService royaltyService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("list.do")
    public String list(Model model)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("customer",Contants.getCustomer().getId());
        entityWrapper.orderBy("time",false);
        List<RoyaltyEntity> list = royaltyService.selectList(entityWrapper);
        model.addAttribute("list",list);
        BigDecimal all = new BigDecimal(0);
        if(list!=null){
            for (RoyaltyEntity royaltyEntity : list) {
                all =  all.add(royaltyEntity.getMoney());
            }
        }
        model.addAttribute("all",all);
        return "mobile/royalty";
    }


    @RequestMapping("tiqu.do")
    @ResponseBody
    public Result tiqu(String money)throws Exception{
        RoyaltyEntity royaltyEntity = new RoyaltyEntity();
        String id = Contants.getCustomer().getId();
        BigDecimal bigDecimal = new BigDecimal(money);
        royaltyEntity.setId(IdWorkerUtil.getId());
        royaltyEntity.setCustomer(id);
        royaltyEntity.setMark("用户提取佣金");
        royaltyEntity.setTime(new Date());
        royaltyEntity.setMoney(bigDecimal);
        royaltyService.insert(royaltyEntity);
        CustomerEntity customerEntity = customerService.selectById(id);
        money = money.replace("-","");
        Integer integer = Integer.valueOf(money);
        customerEntity.setIntegral(customerEntity.getIntegral()+integer/10);
        customerService.updateById(customerEntity);
        return Result.success("提取成功");
    }
}
