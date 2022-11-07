package com.YongChang.service.impl;

import com.YongChang.mapper.ShoppingGatDao;
import com.YongChang.model.ShoppingGatEntity;
import com.YongChang.service.ShoppingGatService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物车
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingGatServiceImpl extends ServiceImpl<ShoppingGatDao,ShoppingGatEntity> implements ShoppingGatService{
}