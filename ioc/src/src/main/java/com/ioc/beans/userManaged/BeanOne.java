package com.ioc.beans.userManaged;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;

public class BeanOne {

    public BeanOne() {
        System.out.println("Initializing Bean1");
    }

    public String toString() {
        return "Bean One";
    }

    @PostConstruct
    public void postCreation() {
        System.out.println("Post Construct");
    }

    @PreDestroy
    public void preDestruction() {
        System.out.println("Pre Destruction");
    }
}
