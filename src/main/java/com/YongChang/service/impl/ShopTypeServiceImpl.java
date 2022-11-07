package com.YongChang.service.impl;

import com.YongChang.mapper.ShopTypeDao;
import com.YongChang.model.ShopTypeEntity;
import com.YongChang.service.ShopTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品类型
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeDao,ShopTypeEntity> implements ShopTypeService{
}