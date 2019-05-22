package com.justdojava.streamdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author 江南一点雨
 * @Date 2019/5/22 16:49
 */
@RestController
public class DelayMsgController {
    @Autowired
    DelayMsgChannel delayMsgChannel;
    @GetMapping("/delay")
    public void hello() {
        System.out.println("message send："+new Date());
        delayMsgChannel.output().send(MessageBuilder.withPayload("delay message!").setHeader("x-delay", 3000).build());
    }
}
