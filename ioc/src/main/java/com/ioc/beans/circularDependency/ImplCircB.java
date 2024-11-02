package com.ioc.beans.circularDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImplCircB {

    @Autowired
    private ImplCircA beanA;
}
