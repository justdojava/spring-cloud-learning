package com.justdojava.consumer;

/**
 * @Author 江南一点雨
 * @Date 2019-04-04 16:20
 */
public class HelloService {
    public static String hello(Integer age) {
        System.out.println("====="+age);
        if (age < 99) {
            try {
                throw new Exception("exception");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "hello>>>" + age;
    }
}
