package com.currency.ly.source.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author wuliangyong
 * @Date 2022/4/7 15:03
 */

/**
 * 在META-INF\spring.factories中定义
 */
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;
    private final String[] args;

    public MySpringApplicationRunListener(SpringApplication sa, String[] args) {
        this.application = sa;
        this.args = args;
    }
    @Override
    public void starting() {
        log.info("starting.........");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("environmentPrepared.........");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("contextPrepared.........");

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("contextLoaded.........");

    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("started.........");

    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("running.........");

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("failed.........");

    }
}
