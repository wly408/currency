package com.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.currency")
public class ApiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiWebApplication.class, args);
    }
}
