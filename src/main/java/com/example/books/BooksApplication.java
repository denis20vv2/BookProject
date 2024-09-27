package com.example.books;

import com.example.books.books.BooksController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class BooksApplication {

	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
		logger.info("start");
	}

}
