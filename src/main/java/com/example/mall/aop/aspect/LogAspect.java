package com.example.mall.aop.aspect;

import com.example.mall.domain.constans.MdcConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.example.mall.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        MDC.put(MdcConstants.MDC_KEY_TRACE_ID, UUID.randomUUID().toString().replaceAll("-", ""));
        String className = joinPoint.getTarget().getClass().getName();    // 类名
        String methodName = joinPoint.getSignature().getName();           // 方法名称
        Object[] args = joinPoint.getArgs();                              // 参数列表

        StringBuilder params = new StringBuilder();
        for (Object arg : args) params.append(arg.toString() + "\t");
        log.info("{}.{}() [请求参数]: {}", className, methodName, params);
    }

    @AfterReturning("webLog()")
    public void afterReturn(){
        MDC.remove(MdcConstants.MDC_KEY_TRACE_ID);
        MDC.remove(MdcConstants.MDC_KEY_USERNAME);
    }
}
