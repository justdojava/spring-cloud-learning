package com.justdojava.resilience4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

public class RetryTest {
    @Test
    public void test1() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(1000))
                .build();
        Retry retry = Retry.of("id", config);

        CheckedFunction0<String> retryableSupplier = Retry.decorateCheckedSupplier(retry, ()->{
            System.out.println(new Date());
            int i = 1 / 0;
            return "hello retry";
        });
        Try<String> result = Try.of(retryableSupplier).recover((throwable) -> "Hello world from recovery function");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }
}
