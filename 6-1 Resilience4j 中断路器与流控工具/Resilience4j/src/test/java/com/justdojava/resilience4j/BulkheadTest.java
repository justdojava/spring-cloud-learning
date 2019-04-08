package com.justdojava.resilience4j;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;

public class BulkheadTest {
    @Test
    public void test1() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(150)
                .maxWaitTime(100)
                .build();
        BulkheadRegistry registry = BulkheadRegistry.of(config);
        Bulkhead bulkhead1 = registry.bulkhead("foo");
        BulkheadConfig custom = BulkheadConfig.custom()
                .maxWaitTime(0)
                .build();
        Bulkhead bulkhead2 = registry.bulkhead("bar", custom);
    }

    @Test
    public void test2() {
        Bulkhead bulkhead1 = Bulkhead.ofDefaults("foo");
        Bulkhead bulkhead2 = Bulkhead.of(
                "bar",
                BulkheadConfig.custom()
                        .maxConcurrentCalls(50)
                        .build()
        );
    }

    @Test
    public void test3() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(1)
                .maxWaitTime(100)
                .build();
        Bulkhead bulkhead = Bulkhead.of("testName", config);
        CheckedFunction0<String> decoratedSupplier = Bulkhead.decorateCheckedSupplier(bulkhead, () -> "This can be any method which returns: 'Hello");
        Try<String> result = Try.of(decoratedSupplier)
                .map(value -> value + " world'");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }
}

