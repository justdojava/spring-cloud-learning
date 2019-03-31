package com.justdojava.feignconsumer;

import com.justdojava.commons.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author 江南一点雨
 * @Date 2019-03-31 21:52
 */
@FeignClient("PROVIDER")
public interface FeignHelloService extends HelloService {
}
