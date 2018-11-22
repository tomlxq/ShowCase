package com.example.demo.aspect.log.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 声明这是一个切面
 */
@Component
@Aspect
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.example.demo.aspect.log.service.impl..*(..))")
    public void pointcut() {
    }

    /**
     * 定义通知
     */
    @AfterReturning(pointcut = "pointcut()", returning = "name")
    void afterReturning(JoinPoint joinPoint, Object name) {
        logger.debug("afterReturning {} {}", name, joinPoint.getSignature().getName());
    }

    @Before("pointcut()")
    void before(JoinPoint joinPoint) {
        logger.debug("before {}", joinPoint.getSignature().getName());
    }

    @Around(value = "pointcut()")
    void around(JoinPoint joinPoint) {
        logger.debug("Around {}", joinPoint.getSignature().getName());
    }

    @After(value = "pointcut()")
    void after(JoinPoint joinPoint) {
        logger.debug("After {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "pointcut()")
    void afterThrowing(JoinPoint joinPoint) {
        logger.debug("afterThrowing {}", joinPoint.getSignature().getName());
    }
}
