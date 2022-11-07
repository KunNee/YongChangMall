package com.YongChang.config;





public final class Result {
    private int code;
    private Object data;
    private String msg;


    private Result() {
    }


    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getStatus();
        this.msg = resultCodeEnum.getDesc();
    }


    public Result(ResultCodeEnum resultCodeEnum, String msg, Object data) {
        this.code = resultCodeEnum.getStatus();
        this.msg = msg;
        if (null == msg || "".equals(msg)) {
            this.msg = resultCodeEnum.getDesc();
        }
        this.data = data;
    }


    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result error(String msg) {
        return new Result(ResultCodeEnum.ERROR, msg, null);
    }

    public static Result error(Object data, String msg) {
        return new Result(ResultCodeEnum.ERROR, msg, data);
    }


    public static Result success(Object data) {
        return new Result(ResultCodeEnum.SUCCESS, null, data);
    }

    public static Result success(Object data, String msg) {
        return new Result(ResultCodeEnum.SUCCESS, msg, data);
    }


    public static Result success(Integer code, Object data, String msg) {
        return new Result(ResultCodeEnum.SUCCESS, msg, data);
    }

    public static Result success(String msg) {
        return new Result(ResultCodeEnum.SUCCESS, msg, null);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
