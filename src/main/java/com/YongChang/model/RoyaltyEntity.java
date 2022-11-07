package com.YongChang.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.YongChang.entity.BaseEntity;
import lombok.Data;
import com.YongChang.table.RoyaltyTable;
import java.util.Date;
import java.math.BigDecimal;



@Data
@TableName("royalty")
public class RoyaltyEntity extends BaseEntity{
  /**
   * 客户ID
   */
  @TableField(RoyaltyTable.CUSTOMER)
  private String customer;
  /**
   * 备注
   */
  @TableField(RoyaltyTable.MARK)
  private String mark;
  /**
   * 
   */
  @TableField(RoyaltyTable.TIME)
  private Date time;
  /**
   * 钱
   */
  @TableField(RoyaltyTable.MONEY)
  private BigDecimal money;
}
