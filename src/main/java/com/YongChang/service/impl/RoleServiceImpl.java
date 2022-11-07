package com.YongChang.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.YongChang.entity.RoleEntity;
import com.YongChang.mapper.RoleDao;
import com.YongChang.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService{
}