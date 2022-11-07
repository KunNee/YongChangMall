package com.YongChang.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.YongChang.entity.BaseEntity;
import lombok.Data;
import com.YongChang.table.ArticleTable;
import java.util.Date;



@Data
@TableName("article")
public class ArticleEntity extends BaseEntity{

  @TableField(ArticleTable.TITLE)
  private String title;

  @TableField(ArticleTable.SUMMARY)
  private String summary;

  @TableField(ArticleTable.CONTENT)
  private String content;

  @TableField(ArticleTable.CHILD_TYPE)
  private String childType;

  @TableField(ArticleTable.CUSTOMER_ID)
  private String customerId;

  @TableField(ArticleTable.TIME)
  private Date time;

  @TableField(ArticleTable.STATUS)
  private Integer status;

  @TableField(ArticleTable.CHECK_RESULT)
  private String checkResult;

  @TableField(ArticleTable.TOP)
  private Boolean top;

  @TableField(ArticleTable.COVER)
  private String cover;

  @TableField(ArticleTable.HOT)
  private Integer hot;

  @TableField(exist = false)
  private String customerName;

  @TableField(exist = false)
  private String childName;

  private String label;

  private String vedio;

  @TableField("shop_ids")
  private String shopIds;

  @TableField(exist = false)
  private CustomerEntity customerEntity;
}
