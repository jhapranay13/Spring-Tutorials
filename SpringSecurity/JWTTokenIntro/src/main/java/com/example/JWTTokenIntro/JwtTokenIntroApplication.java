package com.example.JWTTokenIntro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class JwtTokenIntroApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenIntroApplication.class, args);
	}

}
