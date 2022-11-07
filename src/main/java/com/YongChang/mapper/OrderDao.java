package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.model.OrderEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
}