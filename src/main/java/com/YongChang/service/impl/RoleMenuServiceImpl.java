package com.YongChang.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.YongChang.entity.RoleMenuEntity;
import com.YongChang.mapper.RoleMenuDao;
import com.YongChang.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色菜单表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService{
}