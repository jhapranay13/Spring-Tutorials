package com.ioc.beans.circularDependency;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostCircA {

    @Autowired
    public PostCircB beanB;

    @PostConstruct
    public void init() {
        System.out.println("PostCircA Setting beanA in beanB");
        beanB.setPostCircA(this);
    }
}
