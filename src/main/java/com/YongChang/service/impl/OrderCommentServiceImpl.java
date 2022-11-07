package com.YongChang.service.impl;

import com.YongChang.mapper.OrderCommentDao;
import com.YongChang.model.OrderCommentEntity;
import com.YongChang.service.OrderCommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单评价表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentDao,OrderCommentEntity> implements OrderCommentService{
}