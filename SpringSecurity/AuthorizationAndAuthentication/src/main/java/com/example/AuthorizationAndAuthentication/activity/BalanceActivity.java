package com.example.AuthorizationAndAuthentication.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("balance")
@CrossOrigin(origins = "http://localhost:3000")  // for Cross origin resource sharing(CORS). can be method level as well
public class BalanceActivity {

    @GetMapping
    public ResponseEntity<String> sayHello(Authentication authentication) {
        log.info("Name: {}", authentication.getName());
        return ResponseEntity.ok().body("Hello to Spring security");
    }
}
