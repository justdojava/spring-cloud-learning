package com.justdojava.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(String name) {
        return "hello " + name + " !";
    }

    @PostMapping("/hello2")
    public String sayHello2(String name) {
        return "Hello " + name + " !";
    }

    @GetMapping("/customheader")
    public String customHeader(HttpServletRequest req) {
        return req.getHeader("cookie");
    }
}
