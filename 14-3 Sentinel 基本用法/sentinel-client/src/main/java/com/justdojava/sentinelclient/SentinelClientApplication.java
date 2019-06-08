package com.justdojava.sentinelclient;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SentinelClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelClientApplication.class, args);
    }
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
