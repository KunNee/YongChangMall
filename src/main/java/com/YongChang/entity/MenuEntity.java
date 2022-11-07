package com.YongChang.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import com.YongChang.table.MenuTable;
import java.util.Date;
import java.util.List;



@Data
@TableName("menu")
public class MenuEntity extends BaseEntity{

  @TableField(MenuTable.NAME)
  private String name;

  @TableField(MenuTable.URL)
  private String url;

  @TableField(MenuTable.TYPE)
  private Integer type;

  @TableField(MenuTable.P_ID)
  private String pId;

  @TableField(MenuTable.TIME)
  private Date time;

  @TableField(exist = false)
  private boolean auth;

  @TableField(exist = false)
  private List<MenuEntity> childs;
}
