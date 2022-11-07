package com.YongChang.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.YongChang.entity.BaseEntity;
import lombok.Data;
import com.YongChang.table.OrderTable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



@Data
@TableName("`order`")
public class OrderEntity extends BaseEntity{
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public List<OrderShopEntity> getOrderShops() {
    return orderShops;
  }

  public void setOrderShops(List<OrderShopEntity> orderShops) {
    this.orderShops = orderShops;
  }

  /**
   * 
   */
  @TableField(OrderTable.PRICE)
  private BigDecimal price;
  /**
   * 1 已购买 2 已发货  3已收货  4 已评价
   */
  @TableField(OrderTable.STATUS)
  private Integer status;
  /**
   * 
   */
  @TableField(OrderTable.TIME)
  private Date time;
  /**
   * 地址
   */
  @TableField(OrderTable.ADDRESS)
  private String address;
  /**
   * 收货人
   */
  @TableField(OrderTable.NAME)
  private String name;
  /**
   * 电话
   */
  @TableField(OrderTable.PHONE)
  private String phone;
  /**
   * 客户ID
   */
  @TableField(OrderTable.CUSTOMER_ID)
  private String customerId;

  @TableField(exist = false)
  private List<OrderShopEntity> orderShops;
}
