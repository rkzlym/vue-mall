package com.example.mall.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    INVALID_USERNAME_PASSWORD(400, "用户名或密码错误")
    ;
    private int code;
    private String msg;
}
