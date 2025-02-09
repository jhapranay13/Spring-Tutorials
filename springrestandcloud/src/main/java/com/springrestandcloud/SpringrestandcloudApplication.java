package com.springrestandcloud;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition(
	info = @Info(
				title = "Spring Boot RestApi documentation",
				description = "Testing out",
				version = "v1.0",
				contact = @Contact(
						name = "Pranay",
						email = "jhapranay@gmail.com",
						url = "Some url"
				),
				license = @License(
						name = "Apache 2.0",
						url = "License URL"
				)
			),
	externalDocs = @ExternalDocumentation(
			description = "External Docs",
			url = "ExternalDocsUrl"
	)
)
@SpringBootApplication
@ComponentScan(basePackages = {"com.springrestandcloud"})
public class SpringrestandcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestandcloudApplication.class, args);
	}

}
