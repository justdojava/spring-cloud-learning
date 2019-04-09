package com.justdojava.consumer;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 江南一点雨
 * @Date 2019/4/8 20:08
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @Retry(name = "retryBackendA")
    public String hello(String name) {
        return restTemplate.getForObject("http://provider/hello?name={1}", String.class, name);
    }
}
