package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserDao extends BaseMapper<UserEntity> {


    @Select("select user.*,role.name role_name from user left join role on user.role_id = role.id order by user.time ")
    List<UserEntity> list();
}