package com.justdojava.streamdemo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author 江南一点雨
 * @Date 2019/5/21 21:35
 */
public interface RegChannel {
    String INPUT = "reg-input-channel";
    String OUTPUT = "reg-output-channel";

    @Output(OUTPUT)
    MessageChannel output();
    @Input(INPUT)
    SubscribableChannel input();
}
