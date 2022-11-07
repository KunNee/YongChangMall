package com.YongChang.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.YongChang.config.Result;
import com.YongChang.model.OrderEntity;
import com.YongChang.model.OrderShopEntity;
import com.YongChang.service.OrderService;
import com.YongChang.service.OrderShopService;
import com.YongChang.table.OrderTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderShopService orderShopService;

    @Autowired
    private OrderService orderService;



    @RequestMapping("page.htm")
    public String page(Model model)throws Exception{
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.orderBy(OrderTable.TIME,false);
        List<OrderEntity> list = orderService.selectList(entityWrapper);
        if(list!=null){
            for (OrderEntity orderEntity : list) {
                entityWrapper = new EntityWrapper();
                entityWrapper.eq("order_id",orderEntity.getId());
                List<OrderShopEntity> shoppingGatEntities = orderShopService.selectList(entityWrapper);
                orderEntity.setOrderShops(shoppingGatEntities);
            }
        }
        model.addAttribute("list",list);
        return "order/list";
    }



    @RequestMapping("update.htm")
    @ResponseBody
    public Result update(OrderEntity orderEntity)throws Exception{
        orderService.updateById(orderEntity);
        return Result.success("1");
    }
}
