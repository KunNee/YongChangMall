package com.YongChang.service.impl;

import com.YongChang.mapper.OrderShopDao;
import com.YongChang.model.OrderShopEntity;
import com.YongChang.service.OrderShopService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单购物详情
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderShopServiceImpl extends ServiceImpl<OrderShopDao,OrderShopEntity> implements OrderShopService{
}