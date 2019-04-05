package com.justdojava.resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.internal.AtomicRateLimiter;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

public class ReteLimiterTest {
    @Test
    public void test1() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1000))
                .limitForPeriod(2)
                .timeoutDuration(Duration.ofMillis(1))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
//        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry.rateLimiter("backend");
//        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry.rateLimiter("backend#2", config);
        RateLimiter rateLimiter = RateLimiter.of("NASDAQ :-)", config);

        CheckedRunnable restrictedCall = RateLimiter
                .decorateCheckedRunnable(rateLimiter, () -> {
                    System.out.println(new Date());
                });

        rateLimiter.getEventPublisher()
                .onSuccess(event -> {
                    System.out.println(new Date()+">>>"+event.getEventType()+">>>"+event.getCreationTime());
                })
                .onFailure(event -> {
                    System.out.println(new Date()+">>>"+event.getEventType()+">>>"+event.getCreationTime());
                });
        Try.run(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .onFailure(throwable -> System.out.println(throwable.getMessage()));

        RateLimiter.Metrics metrics = rateLimiter.getMetrics();
        int numberOfThreadsWaitingForPermission = metrics.getNumberOfWaitingThreads();
        int availablePermissions = metrics.getAvailablePermissions();
        System.out.println(numberOfThreadsWaitingForPermission);
        System.out.println(availablePermissions);
    }
}
