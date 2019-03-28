package com.justdojava.feignconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 江南一点雨
 * @Date 2019-03-28 21:09
 */
@FeignClient("PROVIDER")
public interface HelloService {
    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);
}
