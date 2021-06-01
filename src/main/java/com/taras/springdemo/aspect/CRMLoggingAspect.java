package com.taras.springdemo.aspect;

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

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.taras.springdemo.controller.*.*(..))")
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.taras.springdemo.service.*.*(..))")
    private void forServicePackage() {

    }

    @Pointcut("execution(* com.taras.springdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    //add @Before
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        //display method we are calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>>> in @Before: calling method: " + method);

        //display the arguments
        Object[] args = joinPoint.getArgs();

        //display args
        for (Object arg : args) {
            logger.info("=====>>> Argument: " + arg);
        }
    }

    //add @AfterReturning
    @AfterReturning(pointcut = "forAppFlow()",
                    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        //display returning method
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: FROM METHOD: " + method);

        //display returning data
        logger.info("=====>> RESULT: " + result);
    }
}
