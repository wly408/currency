package com.currency.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * https://blog.csdn.net/qidasheng2012/article/details/96494347
 */
@Configuration
public class MyRedissonConfig {

    private RedisProperties redisProperties;
    private static final String REDISSON_PREFIX = "redis://";

    public MyRedissonConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    RedissonClient redisson()  {
        String url =REDISSON_PREFIX+redisProperties.getHost()+":"+redisProperties.getPort();
        Config config = new Config();
        config.useSingleServer()
                .setAddress(url).setPassword(redisProperties.getPassword()).setDatabase(redisProperties.getDatabase());
        return Redisson.create(config);
    }

}