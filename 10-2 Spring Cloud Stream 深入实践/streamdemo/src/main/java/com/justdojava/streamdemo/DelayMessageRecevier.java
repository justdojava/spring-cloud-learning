package com.justdojava.streamdemo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

/**
 * @Author 江南一点雨
 * @Date 2019/5/22 16:26
 */
@EnableBinding(DelayMsgChannel.class)
public class DelayMessageRecevier {
    @StreamListener(DelayMsgChannel.INPUT)
    public void recevier(String msg) {
        System.out.println("receive:" + msg + ">>>" + new Date());
    }

}
