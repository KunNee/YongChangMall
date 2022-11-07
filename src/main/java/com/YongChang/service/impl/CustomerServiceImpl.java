package com.YongChang.service.impl;

import com.YongChang.mapper.CustomerDao;
import com.YongChang.model.CustomerEntity;
import com.YongChang.service.CustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl extends ServiceImpl<CustomerDao,CustomerEntity> implements CustomerService{
}