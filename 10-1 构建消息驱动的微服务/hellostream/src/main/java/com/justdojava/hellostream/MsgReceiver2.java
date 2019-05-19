package com.justdojava.hellostream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Author 江南一点雨
 * @Date 2019/5/19 16:01
 */
@EnableBinding(MyChannel.class)
public class MsgReceiver2 {
    @StreamListener(MyChannel.INPUT)
    public void receive(Object playload) {
        System.out.println("Received2:"+playload);
    }
}
