package com.justdojava.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Date;

/**
 * @Author 江南一点雨
 * @Date 2019/4/8 20:00
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String name) throws InterruptedException {
        String s = "hello " + name + " !";
        System.out.println(s+">>>>>"+new Date());
        return s;
    }
}