package com.learning.simplecrm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {
    private Logger logger = Logger.getLogger(CRMLoggingAspect.class.getName());

    @Pointcut("execution(* com.learning.simplecrm.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.learning.simplecrm.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* com.learning.simplecrm.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @Before: calling method: " + method);

        for (Object arg: joinPoint.getArgs()) {
            logger.info("=====>> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut="forAppFlow()",
            returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: from method: " + method);

        logger.info("=====>> result: " + result);
    }
}
