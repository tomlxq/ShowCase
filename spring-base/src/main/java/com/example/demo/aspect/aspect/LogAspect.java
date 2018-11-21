package com.example.demo.aspect.aspect;

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
    @Pointcut("execution(* com.example.demo.aspect.service.impl..*(..))")
    public void pointcut() {
    }

    /**
     * 定义通知
     */
    @AfterReturning(pointcut = "pointcut()", returning = "name")
    void afterReturning(JoinPoint joinPoint, Object name) {
        logger.debug("{}", name);
        logger.debug("afterReturning");
    }

    @Before("pointcut()")
    void before(JoinPoint joinPoint) {
        logger.debug("before");
    }

    @Around(value = "pointcut()")
    void around(JoinPoint joinPoint) {
        logger.debug("Around");
    }

    @After(value = "pointcut()")
    void after(JoinPoint joinPoint) {
        logger.debug("After");
    }

    @AfterThrowing(value = "pointcut()")
    void afterThrowing(JoinPoint joinPoint) {
        logger.debug("afterThrowing");
    }
}
