package com.YongChang.service.impl;

import com.YongChang.mapper.ArticleShopDao;
import com.YongChang.model.ArticleShopEntity;
import com.YongChang.service.ArticleShopService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章商品表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleShopServiceImpl extends ServiceImpl<ArticleShopDao,ArticleShopEntity> implements ArticleShopService{
}