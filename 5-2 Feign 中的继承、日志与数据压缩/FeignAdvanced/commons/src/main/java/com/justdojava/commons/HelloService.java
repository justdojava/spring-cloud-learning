package com.justdojava.commons;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 江南一点雨
 * @Date 2019-03-31 21:10
 */
public interface HelloService {
    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);
}
