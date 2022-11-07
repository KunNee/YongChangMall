package com.YongChang.controller.pay;

import com.YongChang.model.OrderEntity;
import com.YongChang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/test")
public class return_Out_Url {
    @Autowired
    private Jedis jedis;
    @Autowired
    private OrderService orderService;


    @RequestMapping("return.do")
    public String test(String out_trade_no){
        OrderEntity orderEntity = new OrderEntity();
        if(null!=out_trade_no){
            if(jedis.get("KUN-ORDERID").equals(out_trade_no)){
                orderEntity.setCustomerId(jedis.get("KUN-USERID"));
                orderEntity.setStatus(1);
                orderEntity.setId(out_trade_no);
                if(orderService.updateById(orderEntity)){
                    System.out.println("订单："+out_trade_no+"   已支付成功");
                   return "redirect:/order/list.do";
                }
            }
            return "redirect:/order/list.do";
        }
        return "redirect:/order/list.do";
    }
}
