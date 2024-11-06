package com.aop.aopdemo;

import com.aop.aopdemo.service.Service1;
import com.aop.aopdemo.service.Service2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class AopdemoApplication {

	private Service1 service1;
	private Service2 service2;

	// NJo need for autowiring in constructor injection
	public AopdemoApplication(Service1 service1, Service2 service2) {
		this.service1 = service1;
		this.service2 = service2;
	}

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String ...args) {
		return runner -> {
			service1.getService1();
			service1.getServiceAfterReturning();
			//service1.getServiceWithException();
			service2.service2Method();
			service2.getSetMethod();
			service2.getMethod();
			service2.orderingMethod();
		};
	}

}
