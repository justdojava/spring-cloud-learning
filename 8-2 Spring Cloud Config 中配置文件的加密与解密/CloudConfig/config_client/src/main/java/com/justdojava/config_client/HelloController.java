package com.justdojava.config_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019/5/4 16:44
 */
@RestController
public class HelloController {
    @Value("${javaboy}")
    String hello;
    @GetMapping("/hello")
    public String hello() {
        return hello;
    }
}
