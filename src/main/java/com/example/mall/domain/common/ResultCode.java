package com.example.mall.domain.common;

import lombok.Getter;

/**
 * 返回状态枚举
 */
@Getter
public enum ResultCode {
    SUCCESS(1, "操作成功"),
    FAILED(0, "操作失败"),
    UNAUTHORIZED(0, "暂未登录或token已经过期");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
