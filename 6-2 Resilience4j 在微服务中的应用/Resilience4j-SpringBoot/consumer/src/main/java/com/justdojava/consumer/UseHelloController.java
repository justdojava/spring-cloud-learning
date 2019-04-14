package com.justdojava.consumer;

import com.netflix.discovery.converters.Auto;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

/**
 * @Author 江南一点雨
 * @Date 2019/4/8 20:08
 */
@RestController
public class UseHelloController {
    @Autowired
    HelloService helloService;

    @Autowired
    HelloServiceCircuitBreaker helloServiceCircuitBreaker;

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.hello(name);
    }


    @GetMapping("/hello2")
    public String hello2(String name) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(5000))
                .build();
        Retry retry = Retry.of("id", config);
        Try<String> result = Try.ofSupplier(Retry.decorateSupplier(retry, () -> helloService.hello(name)));
        return result.get();
    }

    @GetMapping("/circuitbreaker")
    public void circuitbreaker(String name) {
        String hello = helloServiceCircuitBreaker.hello(name);
    }

    @GetMapping("/circuitbreaker2")
    public void circuitbreaker2(String name) {
        String hello = helloServiceCircuitBreaker.hello2(name);
        System.out.println(">>>>>>>>" + hello);
    }

    @Autowired
    HelloServiceRateLimiter helloServiceRateLimiter;

    @GetMapping("/rl")
    public void rateLimiter(String name) {
        for (int i = 0; i < 5; i++) {
            String hello = helloServiceRateLimiter.hello(name);
        }
    }
    @GetMapping("/r2")
    public void rateLimiter2(String name) {
        for (int i = 0; i < 5; i++) {
            String hello = helloServiceRateLimiter.hello2(name);
        }
    }
}
