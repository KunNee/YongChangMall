package com.YongChang.controller.pay;

import cn.hutool.core.util.IdUtil;
import com.YongChang.config.Contants;
import com.YongChang.model.CustomerEntity;
import com.YongChang.model.OrderEntity;
import com.YongChang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import java.util.List;


@Controller
public class PayController {
    @Autowired
    @Qualifier("alipayService")
    private AlipayService alipayService;
    @Autowired
    CustomerService customerService;
    @Autowired
    private ShoppingGatService shoppingGatService;
    @Autowired
    private OrderService orderService;
    @Autowired
    public Jedis jedis;

    @RequestMapping("/useIntegral")
    public void useIntegral() {
        System.out.println("true");
        jedis.set("KUN-INTEGRALFLAG", "true");
    }

    @RequestMapping("/unUseIntegral")
    public void unUseIntegral() {
        System.out.println("false");
        jedis.set("KUN-INTEGRALFLAG", "false");
    }

    @RequestMapping("/pay")
    @ResponseBody
    public String payController(@RequestParam("ids") String ids[], @RequestParam("amount") String amount) throws Exception {
        String name = jedis.get("KUN-SHOPID");
        jedis.del("KUN-IDS");
        for (String id : ids) {
            jedis.lpush("KUN-IDS", id);
        }
        double s = Double.parseDouble(amount);
        Integer pay = (int) s;
        Integer flag = (int) s;
        CustomerEntity customer = Contants.getCustomer();
        CustomerEntity customerEntity = customerService.selectById(customer.getId());
        Integer integral = customerEntity.getIntegral();
        String INTEGRALFLA = jedis.get("KUN-INTEGRALFLAG");
        if (integral != null && integral > 0 && "true".equals(INTEGRALFLA)) {
            pay = pay - integral;
            if (pay < 0) {
                pay = 0;
                integral = integral - flag;
            } else {
                integral = 0;
            }
            jedis.set("KUN-FLAG", "true");
            jedis.set("KUN-INTEGRAL", String.valueOf(integral));
            jedis.set("KUN-USERID", customerEntity.getId());
        } else {
            jedis.set("KUN-FLAG", "false");
        }
        name = name.substring(0, name.length() - 1);
        String id =  jedis.get("KUN-ORDERID");
        String pays = alipayService.webPagePay(id, pay, name);
        return pays;
    }

    @RequestMapping("/payresult")
    private String payResult() {
        List<String> ids = jedis.lrange("KUN-IDS", 0, -1);
        jedis.del("KUN-IDS");
        for (String id : ids) {
            shoppingGatService.deleteById(id);
        }
        String flag = jedis.get("KUN-FLAG");
        if ("true".equals(flag)) {
            String integralStr = jedis.get("KUN-INTEGRAL");
            Integer integralInt = Integer.valueOf(integralStr);
            String userId = jedis.get("KUN-USERID");
            CustomerEntity customerEntity = customerService.selectById(userId);
            customerEntity.setIntegral(integralInt);
            customerService.updateById(customerEntity);
        }
        String orderId = jedis.get("KUN-ORDERID");
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setStatus(1);
        orderService.updateById(orderEntity);
        return "shoping/index";
    }
}
