package com.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.currency")
public class MgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MgtApplication.class, args);
    }
}
