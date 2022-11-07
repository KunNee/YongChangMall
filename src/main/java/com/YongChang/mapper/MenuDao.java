package com.YongChang.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.YongChang.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {


    @Select("select * from menu where id in (select menu_id from role_menu where role_id = #{roleId})")
    List<MenuEntity> selectByRoleId(@Param("roleId") String roleId);
}