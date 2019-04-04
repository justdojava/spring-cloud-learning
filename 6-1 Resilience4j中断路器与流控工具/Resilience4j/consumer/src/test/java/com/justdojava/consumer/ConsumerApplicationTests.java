package com.justdojava.consumer;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(20)
                .ringBufferSizeInClosedState(5)
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
//        Function<Integer, String> decorated = CircuitBreaker.decorateFunction(circuitBreaker, HelloService::hello);
        Try<String> result1 = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "hello resilience4j"));
        System.out.println(circuitBreaker.getState());
        System.out.println(result1.get());
    }

    @Test
    public void test1() throws Throwable {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
        CheckedFunction0<Integer> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> 98);
//        Try<String> of = Try.of(decoratedSupplier);
//        System.out.println(of.get());
        CheckedFunction1<Integer, Integer> decoratedFunction = CircuitBreaker
                .decorateCheckedFunction(circuitBreaker, (input) -> {
                    if (input < 99) {
                        System.out.println(">>>>>>>>>>>>>>>>");
                        circuitBreaker.onError(5, new RuntimeException("aaa"));
//                        circuitBreaker.onError(5,new RuntimeException());
                    }
                    return input;
                });
//        Try<Integer> result = Try.of(decoratedSupplier).mapTry(decoratedFunction::apply).recover(throwable -> 99999);
//        System.out.println(result.get());
//        Integer apply = decoratedFunction.apply(98);
//        System.out.println(circuitBreaker.getState());
//        System.out.println(apply);
    }

    @Test
    public void test2() throws Throwable {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
        CheckedFunction0<Integer> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> {
                    throw new RuntimeException("");
                });
        Try<Integer> aTry = Try.of(decoratedSupplier).recover(throwable -> 9999);
        CircuitBreaker.State state = circuitBreaker.getState();
        Integer integer = aTry.get();
        System.out.println(state);
        System.out.println(integer);
    }


    @Test
    public void test3() throws Throwable {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
        CheckedFunction1<Integer, Integer> decoratedFunction = CircuitBreaker
                .decorateCheckedFunction(circuitBreaker, (input) -> {
                    if (input < 99) {
                        System.out.println(">>>>>>>>>>>>>>>>");
                        circuitBreaker.onError(5, new RuntimeException("aaa"));
                    }
                    return input;
                });
    }
}
