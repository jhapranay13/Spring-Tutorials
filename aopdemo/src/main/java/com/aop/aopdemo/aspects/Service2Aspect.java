package com.aop.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class Service2Aspect {
    private Logger logger = LoggerFactory.getLogger(getClass());


    /*@Before("execution(*  com.aop.aopdemo.service.Service1.*(..))")
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
    }*/

    @Around("com.aop.aopdemo.aspects.ServicePointcut.service2PointCut1Before() &&" +
            " !com.aop.aopdemo.aspects.ServicePointcut.service2PointCut1ForGetterSet()")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long execDuration = endTime - startTime;
        logger.info("Around aspect for Service 2 {} executed in {}", joinPoint, execDuration);
        return returnValue;
    }

    @Around("@annotation(com.aop.aopdemo.service.SpecialTracker)")
    public Object executionSpecialTracker(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long execDuration = endTime - startTime;
        logger.info("executionSpecialTracker for Service 2 {} executed in {}", joinPoint, execDuration);
        return returnValue;
    }
}
