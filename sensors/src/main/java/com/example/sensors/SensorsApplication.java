package com.example.sensors;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Entry class for the Spring App.
 * Contains @OpenAPI Annotation to use Swagger.
 */
@OpenAPIDefinition
@SpringBootApplication
public class SensorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorsApplication.class, args);
	}

	/**
	 * Bean created in order to make it possible to inject
	 * RestTemplate in other classes.
	 * @return Instace of RestTemplate.
	 */
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
