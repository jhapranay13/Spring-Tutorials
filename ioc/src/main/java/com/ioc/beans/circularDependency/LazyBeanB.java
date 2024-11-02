package com.ioc.beans.circularDependency;

import org.springframework.stereotype.Component;

@Component
public class LazyBeanB {

    private LazyBeanA beanA;

    public LazyBeanB(LazyBeanA beanA) {
        System.out.println("lazy bean B created");
        this.beanA = beanA;
    }
}
