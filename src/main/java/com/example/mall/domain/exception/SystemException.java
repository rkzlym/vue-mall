package com.example.mall.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SystemException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
