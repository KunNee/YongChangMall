package com.YongChang.service.impl;

import com.YongChang.mapper.CustomerAddressDao;
import com.YongChang.model.CustomerAddressEntity;
import com.YongChang.service.CustomerAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户收货地址
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerAddressServiceImpl extends ServiceImpl<CustomerAddressDao,CustomerAddressEntity> implements CustomerAddressService{
}