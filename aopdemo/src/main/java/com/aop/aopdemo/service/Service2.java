package com.aop.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class Service2 {

    public void service2Method() {
        System.out.println("Service 2");
        try {
            Thread.sleep(45);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SpecialTracker
    public void getSetMethod() {
        System.out.println("Service 2 getSetMethod");
        try {
            Thread.sleep(45);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getMethod() {
        System.out.println("Service 2 getMethod");
        try {
            Thread.sleep(45);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void orderingMethod() {
        System.out.println("Service 2 getMethod");
        try {
            Thread.sleep(45);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
