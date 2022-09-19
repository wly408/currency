package com.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages="com.currency")
public class MgtApplication {

    public static void main(String[] args) {
        //方式1
//        ConfigurableApplicationContext context =  new SpringApplicationBuilder().profiles("").lazyInitialization(false).run(args);
        //方式2
        SpringApplication.run(MgtApplication.class, args);

    }
}
