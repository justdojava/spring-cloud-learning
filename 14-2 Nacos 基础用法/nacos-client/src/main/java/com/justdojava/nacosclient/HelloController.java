package com.justdojava.nacosclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 江南一点雨
 * @Date 2019/6/7 22:52
 */
@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/hello")
    public String hello() {
        return restTemplate.getForObject("http://nacos/hello", String.class);
    }
}
