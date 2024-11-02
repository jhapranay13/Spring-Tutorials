package com.ioc.beans.autoManaged;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class AutoBeanOne {
    public AutoBeanOne() {
        System.out.println("Initializing Bean1");
    }

    public String toString() {
        return "Auto Bean One";
    }

    @PostConstruct
    public void postCreation() {
        System.out.println("Auto Post Construct");
    }

    @PreDestroy
    public void preDestruction() {
        System.out.println("Auto Pre Destruction");
    }
}
