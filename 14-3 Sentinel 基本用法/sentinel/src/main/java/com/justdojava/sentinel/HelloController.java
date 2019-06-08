package com.justdojava.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019/6/8 13:25
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }
}
