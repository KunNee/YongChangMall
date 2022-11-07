package com.YongChang.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.YongChang.entity.MenuEntity;
import com.YongChang.mapper.MenuDao;
import com.YongChang.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService{
}