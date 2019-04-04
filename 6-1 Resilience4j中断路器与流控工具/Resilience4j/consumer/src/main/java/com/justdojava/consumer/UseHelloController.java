package com.justdojava.consumer;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

/**
 * @Author 江南一点雨
 * @Date 2019-04-04 16:06
 */
@RestController
public class UseHelloController {
//    @GetMapping("/hello")
//    public String hello() {
//        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
//                .failureRateThreshold(20)
//                .ringBufferSizeInClosedState(5)
//                .build();
//        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
//        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
//        Function<String, String> decorated = CircuitBreaker
//                .decorateFunction(circuitBreaker, HelloService::hello);
//        decorated.apply("hello");
//        return "Hello";
//    }
}