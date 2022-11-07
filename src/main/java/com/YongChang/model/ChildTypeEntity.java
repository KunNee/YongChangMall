package com.YongChang.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.YongChang.entity.BaseEntity;
import lombok.Data;
import com.YongChang.table.ChildTypeTable;



@Data
@TableName("child_type")
public class ChildTypeEntity extends BaseEntity{
  /**
   * 名称
   */
  @TableField(ChildTypeTable.NAME)
  private String name;
}
