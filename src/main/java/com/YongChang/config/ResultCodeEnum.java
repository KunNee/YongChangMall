package com.YongChang.config;


public enum ResultCodeEnum {
    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败"),
    ;

    private Integer status;

    private String desc;

    ResultCodeEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }


    public Integer getStatus() {
        return status;
    }


    public String getDesc() {
        return desc;
    }
}
