package com.currency.ly.source.springboot.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author wuliangyong
 * @Date 2022/4/7 17:46
 */
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      log.info("MyApplicationContextInitializer.initialize...............");
    }
}
