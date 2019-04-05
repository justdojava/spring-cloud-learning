package com.justdojava.resilience4j;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class TimeLimiterTest {
    @Test
    public void test1() throws Exception {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(60))
                .cancelRunningFuture(true)
                .build();
        TimeLimiter timeLimiter = TimeLimiter.of(config);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Supplier<Future<Integer>> futureSupplier = () -> executorService.submit(backendService::doSomething);
        Callable restrictedCall = TimeLimiter
                .decorateFutureSupplier(timeLimiter, futureSupplier);
        Try.of(restrictedCall.call)
                .onFailure(throwable -> System.out.println(throwable.getMessage()));
    }
}
