package com.example.mall.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回类
 */
@Data
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = -8940366960899264819L;

    private Long code;
    private String msg;
    private T data;

    public CommonResult() {

    }

    public CommonResult(Long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success(String msg, T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> CommonResult<T> success(String msg){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), msg, null);
    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> failed(String msg, T data){
        return new CommonResult<>(ResultCode.FAILED.getCode(), msg, data);
    }

    public static <T> CommonResult<T> failed(String msg){
        return new CommonResult<>(ResultCode.FAILED.getCode(), msg, null);
    }

    public static <T> CommonResult<T> failed(T data){
        return new CommonResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), data);
    }
}
