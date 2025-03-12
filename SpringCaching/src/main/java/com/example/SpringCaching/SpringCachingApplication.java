package com.example.SpringCaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCachingApplication.class, args);
	}

}
