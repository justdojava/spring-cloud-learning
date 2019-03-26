package com.justdojava.consumer;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author 江南一点雨
 * @Date 2019-03-25 20:41
 */
@RestController
public class UseHelloController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;
    int count = 0;

    @GetMapping("/hello")
    public String hello(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(count % list.size());
        count++;
        String host = instance.getHost();
        int port = instance.getPort();
        String s = restTemplate.getForObject("http://" + host + ":" + port + "/hello?name={1}", String.class, name);
        return s;
    }


    @Autowired
    @Qualifier("loadBalancer")
    RestTemplate loadBalancer;

    @GetMapping("/hello2")
    public String hello2(String name) {
        String s = loadBalancer.getForObject("http://provider/hello?name={1}", String.class, name);
        return s;
    }

    @GetMapping("/hello3")
    public void hello3(String name) {
        for (int i = 0; i < 10; i++) {
            String s = loadBalancer.getForObject("http://provider/hello?name={1}", String.class, name);
            System.out.println(s);
        }
    }
}
