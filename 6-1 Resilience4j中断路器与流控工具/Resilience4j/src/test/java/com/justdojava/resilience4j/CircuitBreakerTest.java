package com.justdojava.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.function.Predicate;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static org.hamcrest.CoreMatchers.instanceOf;

public class CircuitBreakerTest {
    @Test
    public void test1() {
        // Create a custom configuration for a CircuitBreaker
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker2 = circuitBreakerRegistry.circuitBreaker("otherName");
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("uniqueName", circuitBreakerConfig);
    }

    @Test
    public void test2() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");
        Try<String> result = Try.of(decoratedSupplier)
                .map(value -> value + " world'");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test3() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CircuitBreaker anotherCircuitBreaker = CircuitBreaker.ofDefaults("anotherTestName");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "Hello");
        CheckedFunction1<String, String> decoratedFunction = CircuitBreaker
                .decorateCheckedFunction(anotherCircuitBreaker, (input) -> input + " world");
        Try<String> result = Try.of(decoratedSupplier)
                .mapTry(decoratedFunction::apply);
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test4() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .ringBufferSizeInClosedState(2)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        Try<String> result = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello"))
                .map(value -> value + " world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test5() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> checkedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
            throw new RuntimeException("BAM!");
        });
        Try<String> result = Try.of(checkedSupplier)
                .recover(throwable -> "Hello Recovery");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test6() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .ringBufferSizeInClosedState(2)
                .ringBufferSizeInHalfOpenState(2)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .recordFailure(e -> (e instanceof ArithmeticException))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        float failureRate = metrics.getFailureRate();
        int bufferedCalls = metrics.getNumberOfBufferedCalls();
        int failedCalls = metrics.getNumberOfFailedCalls();
    }
}
