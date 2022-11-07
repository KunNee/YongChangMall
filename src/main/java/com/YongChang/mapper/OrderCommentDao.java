package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.model.OrderCommentEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderCommentDao extends BaseMapper<OrderCommentEntity> {
}