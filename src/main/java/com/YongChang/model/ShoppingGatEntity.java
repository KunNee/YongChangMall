package com.YongChang.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.YongChang.entity.BaseEntity;
import lombok.Data;
import com.YongChang.table.ShoppingGatTable;



@Data
@TableName("shopping_gat")
public class ShoppingGatEntity extends BaseEntity {
  /**
   * 
   */
  @TableField(ShoppingGatTable.CUSTOMER_ID)
  private String customerId;
  /**
   * 
   */
  @TableField(ShoppingGatTable.SHOP_ID)
  private String shopId;
  /**
   * 数量
   */
  @TableField(ShoppingGatTable.NUM)
  private Integer num;

  @TableField(exist = false)
  private ShopEntity shopEntity;

  private String cid;

}
