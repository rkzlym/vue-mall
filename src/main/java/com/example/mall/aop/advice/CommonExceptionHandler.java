package com.example.mall.aop.advice;

import com.example.mall.domain.constans.MdcConstants;
import com.example.mall.domain.exception.ExceptionEnum;
import com.example.mall.domain.exception.ExceptionResult;
import com.example.mall.domain.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionResult> handleException(Throwable e){
        String traceId = MDC.get(MdcConstants.MDC_KEY_TRACE_ID);
        if (e instanceof SystemException){
            SystemException se = (SystemException) e;
            ExceptionEnum em = se.getExceptionEnum();
            return ResponseEntity.status(em.getCode()).body(new ExceptionResult(em, traceId));
        }
        log.error("时间戳: {}", traceId, e);
        return ResponseEntity.status(500).body(new ExceptionResult(traceId));
    }
}
