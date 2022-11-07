package com.YongChang.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import com.YongChang.table.RoleMenuTable;



@Data
@TableName("role_menu")
public class RoleMenuEntity extends BaseEntity{

  @TableField(RoleMenuTable.ROLE_ID)
  private String roleId;

  @TableField(RoleMenuTable.MENU_ID)
  private String menuId;
}
