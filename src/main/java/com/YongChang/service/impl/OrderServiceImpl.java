package com.YongChang.service.impl;

import com.YongChang.mapper.OrderDao;
import com.YongChang.model.OrderEntity;
import com.YongChang.service.OrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderDao,OrderEntity> implements OrderService{
}