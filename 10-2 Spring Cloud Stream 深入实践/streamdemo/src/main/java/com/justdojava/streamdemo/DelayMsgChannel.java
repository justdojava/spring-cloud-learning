package com.justdojava.streamdemo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author 江南一点雨
 * @Date 2019/5/22 16:23
 */
public interface DelayMsgChannel {
    String INPUT = "delay_msg_input";
    String OUTPUT = "delay_msg_output";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();
}
