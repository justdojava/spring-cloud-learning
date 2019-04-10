package com.justdojava.consumer;

import java.util.function.Predicate;

/**
 * @Author 江南一点雨
 * @Date 2019-04-10 11:06
 */
public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        return true;
    }
}
