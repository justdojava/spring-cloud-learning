package com.justdojava.provider;

import com.justdojava.commons.HelloService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019-03-31 21:31
 */
@RestController
public class HelloController implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println(">>>>>>>>>>>>");
//        int i = 1 / 0;
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name + " !";
    }
}
