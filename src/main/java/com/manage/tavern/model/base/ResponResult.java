package com.manage.tavern.model.base;


import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Author dll
 * @create 2020/5/31 11:16
 * @describe
 */
@Data
public class ResponResult<T> {

    private Integer status;

    private String msg;

    private T obj;

    protected ResponResult() {
    }

    protected ResponResult(Integer status, String msg, T obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    protected ResponResult(Integer status) {
        this.status = status;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     */
    public static <T> ResponResult<T> success(T data) {
        return new ResponResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> ResponResult<T> success() {
        return new ResponResult<T>(ResultCode.SUCCESS.getCode());
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResponResult<T> success(T data, String message) {
        return new ResponResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResponResult<T> failed() {
        return new ResponResult<T>(ResultCode.IErrorCode.getCode(), ResultCode.IErrorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponResult<T> failed(String message) {
        return new ResponResult<T>(ResultCode.IErrorCode.getCode(), message, null);
    }

}
