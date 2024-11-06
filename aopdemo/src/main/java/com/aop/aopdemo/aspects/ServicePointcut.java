package com.aop.aopdemo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ServicePointcut {

    @Pointcut("bean(service2)")
    public void service2PointCut1Before() {}

    @Pointcut("execution(* com.aop.aopdemo.service.Service2.get*(..))")
    public void service2PointCut1ForGetter() {}

    @Pointcut("execution(* com.aop.aopdemo.service.Service2.getSet*(..))")
    public void service2PointCut1ForGetterSet() {}

    @Pointcut("execution(* com.aop.aopdemo.service.Service2.orderingMethod(..))")
    public void orderedPointCut() {}
}
