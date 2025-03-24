package com.example.SpringSecurityIntro.activity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("balance")
public class BalanceActivity {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok().body("Hello to Spring security");
    }
}
