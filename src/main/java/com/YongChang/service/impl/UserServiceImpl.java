package com.YongChang.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.YongChang.entity.UserEntity;
import com.YongChang.mapper.UserDao;
import com.YongChang.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService{
}