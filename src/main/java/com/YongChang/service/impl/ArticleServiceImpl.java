package com.YongChang.service.impl;

import com.YongChang.mapper.ArticleDao;
import com.YongChang.model.ArticleEntity;
import com.YongChang.service.ArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl extends ServiceImpl<ArticleDao,ArticleEntity> implements ArticleService{
}