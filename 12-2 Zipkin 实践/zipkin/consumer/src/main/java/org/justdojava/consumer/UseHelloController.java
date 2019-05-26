package org.justdojava.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 江南一点雨
 * @Date 2019/5/26 21:10
 */
@RestController
public class UseHelloController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/sayhello")
    public void hello() {
        String s = restTemplate.getForObject("http://localhost:8080/hello?name={1}", String.class, "javaboy");
        System.out.println(s);
    }

}
