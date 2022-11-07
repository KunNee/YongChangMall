package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.model.CustomerAddressEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CustomerAddressDao extends BaseMapper<CustomerAddressEntity> {
}