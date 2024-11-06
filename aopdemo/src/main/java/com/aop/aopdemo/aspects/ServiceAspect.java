package com.aop.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ServiceAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    // Pointcut: - When to execute expression
    // advice: - what code to execute
    // Aspect: - Combination of above two
    // weaver:- Framework that runs AOP spring or aspectJ
    // (..) 0 or more number of args. (*) one or more number
    // execution(returnval fullyqualifiedMethod(arguments))
    @Before("execution(*  com.aop.aopdemo.service.Service1.*(..))")
    public void service1BeforeMethodCall(JoinPoint joinpoint) {
        logger.info("Method is called - {} with args {}", joinpoint, joinpoint.getArgs());
    }

    // executes even after exception
    @After("execution(*  com.aop.aopdemo.service.Service1.*(..))")
    public void service1AfterMethodCall(JoinPoint joinpoint) {
        logger.info("Method After called - {} with args {}", joinpoint, joinpoint.getArgs());
    }


    @AfterReturning(
            pointcut = "execution(*  com.aop.aopdemo.service.Service1.*(..))",
            returning = "returnVal"
    )
    public void service1AfterReturningMethodCall(JoinPoint joinpoint, Object returnVal) {
        logger.info("Method After returning called - {} with args {} with return val {}", joinpoint,
                joinpoint.getArgs(), returnVal);
    }

    @AfterThrowing(
            pointcut = "execution(*  com.aop.aopdemo.service.Service1.*(..))",
            throwing = "exception"
    )
    public void service1AfterExceptionMethodCall(JoinPoint joinpoint, Exception exception) {
        logger.info("Method After Throwing called - {} with args {} and with exception {}", joinpoint,
                joinpoint.getArgs(), exception);
    }

    @Around("execution(*  com.aop.aopdemo.service.Service1.*(..))")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long execDuration = endTime - startTime;
        logger.info("Around aspect {} executed in {}", joinPoint, execDuration);
        return returnValue;
    }
}
