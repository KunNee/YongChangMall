package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.model.OrderShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderShopDao extends BaseMapper<OrderShopEntity> {


    @Select("SELECT AVG(score)num FROM order_shop WHERE shop_id = '${sid}' and score is not NULL ")
    Double num(@Param("sid")String sid);

    @Select("SELECT order_shop.*,customer.`name` cname,customer.header FROM order_shop LEFT JOIN customer ON order_shop.customer_id = customer.id WHERE shop_id = '${sid}' and  content is not null ")
    List<OrderShopEntity> list(@Param("sid")String sid);
}