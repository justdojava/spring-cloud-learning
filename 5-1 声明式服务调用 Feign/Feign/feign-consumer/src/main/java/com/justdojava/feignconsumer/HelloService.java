package com.justdojava.feignconsumer;

import com.justdojava.commons.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 江南一点雨
 * @Date 2019-03-28 21:09
 */
@FeignClient("provider")
public interface HelloService {
    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);
    @DeleteMapping("/user/{id}")
    void deleteUserById(@PathVariable("id") Long id);

    @GetMapping("/user")
    User getUserByName(@RequestParam("name") String name);

    @PostMapping("/user")
    User addUser(@RequestBody User user);

    @PutMapping("/user")
    void updateUserById(@RequestHeader("name") String name, @RequestHeader("id") Long id);
}
