package com.justdojava.hellostream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Author 江南一点雨
 * @Date 2019/5/19 16:01
 */
@EnableBinding(Sink.class)
public class MsgReceiver {
    @StreamListener(Sink.INPUT)
    public void receive(Object playload) {
        System.out.println("Received:"+playload);
    }
}
