package com.justdojava.sentinelclient;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 江南一点雨
 * @Date 2019/6/8 14:33
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;
//    @SentinelResource(value = "hello",fallback = "error")
    public String hello() {
        return restTemplate.getForObject("http://localhost:8081/hello2", String.class);
    }
    public String error() {
        return "error";
    }
}
