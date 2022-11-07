package com.YongChang.config.exception;

/**
 * @author YongChang
 */
public class NeedLoginException extends RuntimeException{
    private String message;

    public NeedLoginException(String message) {
        this.message = message;
    }

    public NeedLoginException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
