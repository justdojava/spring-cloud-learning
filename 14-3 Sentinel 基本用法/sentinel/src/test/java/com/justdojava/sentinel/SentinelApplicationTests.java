package com.justdojava.sentinel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentinelApplicationTests {

    @Test
    public void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 15; i++) {
            String s = restTemplate.getForObject("http://localhost:8081/hello", String.class);
            System.out.println(i + ":" + s+":"+new Date());
        }
    }

}
