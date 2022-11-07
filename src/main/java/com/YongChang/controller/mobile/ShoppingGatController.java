package com.YongChang.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.YongChang.config.Contants;
import com.YongChang.config.Result;
import com.YongChang.model.CustomerAddressEntity;
import com.YongChang.model.CustomerEntity;
import com.YongChang.model.ShopEntity;
import com.YongChang.model.ShoppingGatEntity;
import com.YongChang.service.*;
import com.YongChang.table.ShoppingGatTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("shoppingGat")
public class ShoppingGatController {

    @Autowired
    private ShoppingGatService shoppingGatService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RoyaltyService royaltyService;



    @RequestMapping("list.do")
    public String list(Model model)throws Exception{
        CustomerEntity userEntity = Contants.getCustomer();
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(ShoppingGatTable.CUSTOMER_ID,userEntity.getId());
        List<ShoppingGatEntity> gats = shoppingGatService.selectList(entityWrapper);
        if(gats!=null ){
            for (ShoppingGatEntity gat : gats) {
                ShopEntity shopEntity = shopService.selectById(gat.getShopId());
                gat.setShopEntity(shopEntity);
            }
        }
        model.addAttribute("gats",gats);
        CustomerEntity customerEntity = customerService.selectById(userEntity.getId());
        model.addAttribute("integral",customerEntity.getIntegral());
       List<CustomerAddressEntity> customerAddressEntities =   customerAddressService.selectList(entityWrapper);
        model.addAttribute("customerAddressEntities",customerAddressEntities);
        return "mobile/gat";
    }



    @RequestMapping("delete.do")
    @ResponseBody
    public Result delete(String id)throws Exception{
        CustomerEntity userEntity = Contants.getCustomer();
        if(StringUtils.isEmpty(id)){
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq(ShoppingGatTable.CUSTOMER_ID,userEntity.getId());
            shoppingGatService.delete(entityWrapper);
        }else{
            shoppingGatService.deleteById(id);
        }
        return Result.success("成功");
    }


    @RequestMapping("add.do")
    @ResponseBody
    public Result add(String shopId,Integer num,String customerId)throws Exception{
        CustomerEntity userEntity = Contants.getCustomer();
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(ShoppingGatTable.CUSTOMER_ID,userEntity.getId())
                .eq(ShoppingGatTable.SHOP_ID,shopId);
        ShoppingGatEntity gatEntity = shoppingGatService.selectOne(entityWrapper);
        if(gatEntity!=null){
            gatEntity.setNum(gatEntity.getNum()+num);
            shoppingGatService.updateById(gatEntity);
        }else{
            gatEntity = new ShoppingGatEntity();
            gatEntity.setId(IdWorker.get32UUID());
            gatEntity.setNum(num);
            gatEntity.setShopId(shopId);
            gatEntity.setCustomerId(userEntity.getId());
            gatEntity.setCid(customerId);
            shoppingGatService.insert(gatEntity);
        }
        return Result.success("成功");
    }



}
