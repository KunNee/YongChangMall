package com.YongChang.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.YongChang.entity.BaseEntity;
import lombok.Data;
import com.YongChang.table.ShopTypeTable;



@Data
@TableName("shop_type")
public class ShopTypeEntity extends BaseEntity{
  /**
   * 名称
   */
  @TableField(ShopTypeTable.NAME)
  private String name;
}
