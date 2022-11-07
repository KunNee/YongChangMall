package com.YongChang.service.impl;

import com.YongChang.mapper.RoyaltyDao;
import com.YongChang.model.RoyaltyEntity;
import com.YongChang.service.RoyaltyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户提成表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoyaltyServiceImpl extends ServiceImpl<RoyaltyDao,RoyaltyEntity> implements RoyaltyService{
}