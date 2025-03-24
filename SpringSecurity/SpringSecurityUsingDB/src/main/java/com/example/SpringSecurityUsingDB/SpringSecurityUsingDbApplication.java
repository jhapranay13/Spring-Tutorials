package com.example.SpringSecurityUsingDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity   // It's optional
@SpringBootApplication
public class SpringSecurityUsingDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityUsingDbApplication.class, args);
	}

}
