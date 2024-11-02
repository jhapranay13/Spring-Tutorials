package com.ioc.beans.circularDependency;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LazyBeanA {

    private LazyBeanB beanB;

    // Lazy can be used in setter injection on both sides so that will also resolve the circular dependency
    public LazyBeanA(@Lazy LazyBeanB beanB) {
        System.out.println("lazy bean A created");
        this.beanB = beanB;
    }
}
