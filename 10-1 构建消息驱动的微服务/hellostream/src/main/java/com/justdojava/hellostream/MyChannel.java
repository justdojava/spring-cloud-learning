package com.justdojava.hellostream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author 江南一点雨
 * @Date 2019/5/19 16:58
 */
public interface MyChannel {
    String INPUT = "mychannel-input";
    String OUTPUT = "mychannel-output";

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();
}
