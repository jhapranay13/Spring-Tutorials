package com.aop.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.IntStream;

@Service
public class Service1 {
    public void getService1() {
        System.out.println("It's up");
    }

    public IntStream getServiceAfterReturning() throws Exception {
        return Arrays.stream(new int[]{1, 2, 3, 4});
    }

    public void getServiceWithException() throws Exception {
        throw new Exception();
    }
}
