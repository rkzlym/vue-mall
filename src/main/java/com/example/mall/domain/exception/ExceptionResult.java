package com.example.mall.domain.exception;

import lombok.Data;

@Data
public class ExceptionResult {
    private int status;
    private String message;
    private String traceId;

    public ExceptionResult(ExceptionEnum em, String traceId){
        this.status = em.getCode();
        this.message = em.getMsg();
        this.traceId = traceId;
    }

    public ExceptionResult(String traceId){
        this.status = 500;
        this.message = "系统内部异常";
        this.traceId = traceId;
    }
}
