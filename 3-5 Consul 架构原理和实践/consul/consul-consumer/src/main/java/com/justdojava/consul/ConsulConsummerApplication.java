package com.justdojava.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulConsummerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsummerApplication.class, args);
    }

}
