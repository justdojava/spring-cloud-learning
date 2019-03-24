package com.justdojava.consumer;

import com.justdojava.commons.User;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class UseHelloController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello?name={1}";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, name);
        StringBuffer sb = new StringBuffer();
        HttpStatus statusCode = responseEntity.getStatusCode();
        String body = responseEntity.getBody();
        sb.append("statusCode：")
                .append(statusCode)
                .append("</br>")
                .append("body：")
                .append(body)
                .append("</br>");
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }
        return sb.toString();
    }

    @GetMapping("/hello2")
    public String hello2(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        Map<String, Object> map = new HashMap<>();
        String url = "http://" + host + ":" + port + "/hello?name={name}";
        map.put("name", name);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
        StringBuffer sb = new StringBuffer();
        HttpStatus statusCode = responseEntity.getStatusCode();
        String body = responseEntity.getBody();
        sb.append("statusCode：")
                .append(statusCode)
                .append("</br>")
                .append("body：")
                .append(body)
                .append("</br>");
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }
        return sb.toString();
    }


    @GetMapping("/hello3")
    public String hello3(String name) throws UnsupportedEncodingException {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello?name=" + URLEncoder.encode(name, "UTF-8");
        URI uri = URI.create(url);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        StringBuffer sb = new StringBuffer();
        HttpStatus statusCode = responseEntity.getStatusCode();
        String body = responseEntity.getBody();
        sb.append("statusCode：")
                .append(statusCode)
                .append("</br>")
                .append("body：")
                .append(body)
                .append("</br>");
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }
        return sb.toString();
    }

    @GetMapping("/hello4")
    public String hello4(String name) throws UnsupportedEncodingException {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello?name=" + URLEncoder.encode(name, "UTF-8");
        URI uri = URI.create(url);
        String s = restTemplate.getForObject(uri, String.class);
        return s;
    }


    @GetMapping("/hello5")
    public String hello5(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello2";
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("name", name);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, map, String.class);
        return responseEntity.getBody();
    }


    @GetMapping("/hello6")
    public String hello6(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello2?name={1}";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, null, String.class,name);
        return responseEntity.getBody();
    }

    @GetMapping("/hello7")
    public User hello7() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/user";
        User u1 = new User();
        u1.setUsername("牧码小子");
        u1.setAddress("深圳");
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, u1, User.class);
        return responseEntity.getBody();
    }

    @GetMapping("/hello8")
    public String hello8() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/register";
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "牧码小子");
        map.add("address", "深圳");
        URI uri = restTemplate.postForLocation(url, map);
        String s = restTemplate.getForObject(uri, String.class);
        return s;
    }


    @GetMapping("/hello9")
    public void hello9() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url1 = "http://" + host + ":" + port + "/user/name";
        String url2 = "http://" + host + ":" + port + "/user/address";
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "牧码小子");
        map.add("address", "深圳");
        restTemplate.put(url1, map);
        User u1 = new User();
        u1.setAddress("广州");
        u1.setUsername("江南一点雨");
        restTemplate.put(url2, u1);
    }

    @GetMapping("/hello10")
    public void hello10() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url1 = "http://" + host + ":" + port + "/user/{1}";
        String url2 = "http://" + host + ":" + port + "/user/?username={username}";
        Map<String,String> map = new HashMap<>();
        map.put("username", "牧码小子");
        restTemplate.delete(url1, 99);
        restTemplate.delete(url2, map);
    }

    @GetMapping("/hello11")
    public void hello11() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/customheader";
        restTemplate.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                HttpHeaders headers = request.getHeaders();
                headers.add("cookie","justdojava");
                return execution.execute(request,body);
            }
        }));
        String s = restTemplate.getForObject(url, String.class);
        System.out.println(s);
    }

    @GetMapping("/hello12")
    public void hello12() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/customheader";
        HttpHeaders headers = new HttpHeaders();
        headers.add("cookie","justdojava");
        HttpEntity<MultiValueMap<String,String>> request =  new HttpEntity<>(null,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        System.out.println(responseEntity.getBody());
    }
}