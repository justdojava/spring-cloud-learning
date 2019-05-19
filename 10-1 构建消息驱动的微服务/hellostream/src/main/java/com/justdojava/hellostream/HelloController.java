package com.justdojava.hellostream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019/5/19 19:57
 */
@RestController
public class HelloController {
    @Autowired
    MyChannel myChannel;

    @GetMapping("/test1")
    public void hello() {
        myChannel.output().send(MessageBuilder.withPayload("hello stream!").build());
    }
}