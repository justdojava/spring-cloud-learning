package com.justdojava.provider;

import com.justdojava.commons.User;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author 江南一点雨
 * @Date 2019-03-29 21:08
 */
@RestController
public class UserController {

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Long id) {
        System.out.println(id);
    }

    @GetMapping("/user")
    public User getUserByName(@RequestParam String name) {
        User user = new User();
        user.setUsername(name);
        return user;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return user;
    }

    @PutMapping("/user")
    public void updateUserById(@RequestHeader String name, @RequestHeader Long id) {
        System.out.println("name:" + name + ";id:" + id);
    }

}
