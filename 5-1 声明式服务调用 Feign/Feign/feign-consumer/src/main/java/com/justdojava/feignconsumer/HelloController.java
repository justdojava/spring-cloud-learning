package com.justdojava.feignconsumer;

import com.justdojava.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019-03-28 21:10
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.hello(name);
    }

    @GetMapping("/hello2")
    public void hello2() {
        helloService.deleteUserById(99L);
        User u2 = helloService.getUserByName("纯洁的微笑");
        System.out.println(u2);
        u2.setId(98L);
        u2.setAddress("深圳");
        User u3 = helloService.addUser(u2);
        System.out.println(u3);
        helloService.updateUserById("lenve", 99L);
    }
}
