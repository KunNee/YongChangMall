package com.YongChang.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import com.YongChang.table.RoleTable;
import java.util.Date;



@Data
@TableName("role")
public class RoleEntity extends BaseEntity{

  @TableField(RoleTable.NAME)
  private String name;

  @TableField(RoleTable.STATUS)
  private Boolean status;

  @TableField(RoleTable.TIME)
  private Date time;
}
