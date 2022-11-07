package com.YongChang.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import com.YongChang.table.UserTable;
import java.util.Date;



@Data
@TableName("user")
public class UserEntity extends BaseEntity{

  @TableField(UserTable.NAME)
  private String name;

  @TableField(UserTable.LOGIN_NAME)
  private String loginName;

  @TableField(UserTable.PASSWORD)
  private String password;

  @TableField(UserTable.SEX)
  private Boolean sex;

  @TableField(UserTable.PHONE)
  private String phone;

  @TableField(UserTable.MARK)
  private String mark;

  @TableField(UserTable.HEAD_IMG)
  private String headImg;

  @TableField(UserTable.STATUS)
  private Boolean status;

  @TableField(UserTable.ROLE_ID)
  private String roleId;

  @TableField(UserTable.TIME)
  private Date time;


  @TableField(value="role_name",exist = false)
  private String roleName;
}
