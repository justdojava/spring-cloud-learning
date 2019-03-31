package com.justdojava.feignconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019-03-31 21:59
 */
@RestController
public class UseHelloController {
    @Autowired
    FeignHelloService feignHelloService;
    @GetMapping("/hello")
    public String hello(String name) {
        return feignHelloService.hello(name);
    }
}
