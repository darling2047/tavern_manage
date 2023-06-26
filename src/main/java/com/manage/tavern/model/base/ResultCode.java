package com.manage.tavern.model.base;

/**
 * @author: huangfuliang
 * @date: 2019-08-12.
 */
public enum ResultCode {
    /**
     * 返回成功
     */
    SUCCESS(0, "返回成功"),

    IErrorCode(1, "网络繁忙");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
