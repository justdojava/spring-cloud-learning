package com.justdojava.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019/6/7 20:28
 */
@RestController
@RefreshScope
public class HelloController {
    @Value(value = "${name}")
    String name;
    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello() {
        return "hello " + name + ">>>" + port;
    }
}