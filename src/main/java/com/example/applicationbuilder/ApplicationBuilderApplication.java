package com.example.applicationbuilder;

import com.example.authorizationservice.AuthorizationServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

public class ApplicationBuilderApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ApplicationBuilderApplication.class, args);

		new SpringApplicationBuilder(AuthorizationServiceApplication.class)
				.properties("spring.config.name=authorization-service")
				.run(args);

		/*new SpringApplicationBuilder(ViewerServiceApplication.class)
				.properties("spring.config.name=application-storage")
				.properties("server.port=8080")
				.run(args);*/

	}

}
