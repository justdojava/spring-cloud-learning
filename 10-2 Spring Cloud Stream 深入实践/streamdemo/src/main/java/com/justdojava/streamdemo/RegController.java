package com.justdojava.streamdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 江南一点雨
 * @Date 2019/5/21 21:33
 */
@RestController
public class RegController {
    @Autowired
    RegService regService;
    @PostMapping("/doReg")
    public Map<String, Object> reg(String email, String phone, String password) {
        return regService.reg(email, phone, password);
    }
}
