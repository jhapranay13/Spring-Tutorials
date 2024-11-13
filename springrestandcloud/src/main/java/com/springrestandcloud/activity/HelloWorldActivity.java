package com.springrestandcloud.activity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldActivity {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello world";
    }

}
