package com.YongChang.service.impl;

import com.YongChang.mapper.ChildTypeDao;
import com.YongChang.model.ChildTypeEntity;
import com.YongChang.service.ChildTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分类表
 * @author Kun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChildTypeServiceImpl extends ServiceImpl<ChildTypeDao,ChildTypeEntity> implements ChildTypeService{
}