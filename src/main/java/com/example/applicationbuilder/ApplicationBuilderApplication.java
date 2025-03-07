package com.example.applicationbuilder;


import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.authorizationservice.AuthorizationServiceApplication;

public class ApplicationBuilderApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ApplicationBuilderApplication.class, args);

		new SpringApplicationBuilder(AuthorizationServiceApplication.class)
				.properties("spring.config.name=authorization-service")
				.properties("server.port=8080")
				.run(args);

		/*new SpringApplicationBuilder(ViewerServiceApplication.class)
				.properties("spring.config.name=application-storage")
				.properties("server.port=8080")
				.run(args);*/

	}

}
