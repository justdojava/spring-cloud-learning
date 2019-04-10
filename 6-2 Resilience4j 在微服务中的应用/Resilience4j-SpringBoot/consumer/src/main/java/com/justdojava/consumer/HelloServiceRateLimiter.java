package com.justdojava.consumer;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 江南一点雨
 * @Date 2019-04-10 21:45
 */
@Service
@RateLimiter(name = "backendA")
public class HelloServiceRateLimiter {
    @Autowired
    RestTemplate restTemplate;
    public String hello(String name) {
        return restTemplate.getForObject("http://provider/hello?name={1}", String.class, name);
    }
}
