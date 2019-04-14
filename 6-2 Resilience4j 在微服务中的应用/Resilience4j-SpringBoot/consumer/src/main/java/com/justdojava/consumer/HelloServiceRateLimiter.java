package com.justdojava.consumer;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

/**
 * @Author 江南一点雨
 * @Date 2019-04-10 21:45
 */
@Service
//@RateLimiter(name = "backendA")
public class HelloServiceRateLimiter {
    @Autowired
    RestTemplate restTemplate;

    public String hello(String name) {
        return restTemplate.getForObject("http://provider/hello?name={1}", String.class, name);
    }

    public String hello2(String name) {
        System.out.println("hello2>>>>>>>>");
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(50000))
                .limitForPeriod(1)
                .timeoutDuration(Duration.ofMillis(5000))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
//        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("backendB",config);
        RateLimiter rateLimiter = RateLimiter.of("NASDAQ :-)", config);
        Try<String> aTry = Try.ofSupplier(RateLimiter.decorateSupplier(rateLimiter, () ->
                restTemplate.getForObject("http://provider/hello?name={1}", String.class, name)
        ))
                .recover(RequestNotPermitted.class, "请求不被允许");
        return aTry.get();
    }
}
