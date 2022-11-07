package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.model.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {
}