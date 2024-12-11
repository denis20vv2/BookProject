package com.example.main;

import com.example.shop.ShopApplication;
import com.example.storage.StorageApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class MainApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ShopApplication.class)
                .properties("spring.config.name=application-shop") 
                .properties("server.port=8081")
                .run(args);


        new SpringApplicationBuilder(StorageApplication.class)
                .properties("spring.config.name=application-storage")
                .properties("server.port=8080")
                .run(args);
    }

}

