package com.justdojava.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019/4/8 20:08
 */
@RestController
public class UseHelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.hello(name);
    }
}
