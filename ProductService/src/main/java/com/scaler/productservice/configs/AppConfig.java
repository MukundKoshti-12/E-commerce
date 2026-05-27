package com.scaler.productservice.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration // This will describe that the below class contains all the beans declarations which are required
public class AppConfig {
    @Bean // This is a bean which will get used automatically when we inject it in a constructor
    @LoadBalanced
    public RestTemplate createRestTemplateBean(){
        return new RestTemplate();
    }

    @Bean
    public RedisTemplate<String, Object> createRedisTemplateBean(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
