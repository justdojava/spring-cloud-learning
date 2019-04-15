package com.justdojava.consumer;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Date;
import java.util.function.Supplier;
import java.util.stream.IntStream;

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

    public void hello2(String name) {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(5000))
                .limitForPeriod(1)
                .timeoutDuration(Duration.ofMillis(6000))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiter = RateLimiter.of("backendB", config);
        Supplier<String> supplier = RateLimiter.decorateSupplier(rateLimiter, () ->
                restTemplate.getForObject("http://provider/hello?name={1}", String.class, name)
        );
        for (int i = 0; i < 5; i++) {
            Try<String> aTry = Try.ofSupplier(supplier);
            System.out.println(aTry.get());
        }
    }
}
