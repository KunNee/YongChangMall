package com.YongChang.service.impl;

import com.YongChang.mapper.ShopDao;
import com.YongChang.model.ShopEntity;
import com.YongChang.service.ShopService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl extends ServiceImpl<ShopDao,ShopEntity> implements ShopService{
}