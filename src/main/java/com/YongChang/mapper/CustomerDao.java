package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.model.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {
}