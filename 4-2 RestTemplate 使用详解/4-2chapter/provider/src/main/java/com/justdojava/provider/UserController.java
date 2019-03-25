package com.justdojava.provider;

import com.justdojava.commons.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class UserController {
    @ResponseBody
    @PostMapping("/user")
    public User hello(@RequestBody User user) {
        return user;
    }

    @RequestMapping("/register")
    public String register(User user) throws UnsupportedEncodingException {
        return "redirect:/loginPage?username=" + URLEncoder.encode(user.getUsername(),"UTF-8") + "&address=" + URLEncoder.encode(user.getAddress(),"UTF-8");
    }

    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(User user) {
        return "loginPage:" + user.getUsername() + ":" + user.getAddress();
    }


    @PutMapping("/user/name")
    @ResponseBody
    public void updateUserByUsername(User User) {
        System.out.println(User);
    }

    @PutMapping("/user/address")
    @ResponseBody
    public void updateUserByAddress(@RequestBody User User) {
        System.out.println(User);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public void deleteUserById(@PathVariable Integer id) {
        System.out.println(id);
    }

    @DeleteMapping("/user/")
    @ResponseBody
    public void deleteUserByUsername(String username) {
        System.out.println(username);
    }
}
