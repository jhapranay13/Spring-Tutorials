package com.aop.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Aspect
@Order(2)
public class OrderedAspect2 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("com.aop.aopdemo.aspects.ServicePointcut.orderedPointCut()")
    public void orderPointCutAspect1(JoinPoint joinPoint) {
        logger.info("Ordering the aspect OrderedAspect2 {}", joinPoint);
    }
}
