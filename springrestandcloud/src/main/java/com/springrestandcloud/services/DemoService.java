package com.springrestandcloud.services;


import com.springrestandcloud.exceptions.ResourceNotFoundException;
import com.springrestandcloud.exceptions.SomeGlobalException;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public void serviceForException() {
        throw new ResourceNotFoundException("Some resource", "Some field", 23L);
    }

    public void serviceForGlobalException() {
        throw new SomeGlobalException("Some global", "global field", 23L);
    }
}
