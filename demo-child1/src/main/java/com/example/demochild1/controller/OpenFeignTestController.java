package com.example.demochild1.controller;

import com.example.demochild1.Service.HelloService;
import com.example.demochild1.feign.OpenFeignTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RequestMapping
@RestController
public class OpenFeignTestController {
    @Autowired
    OpenFeignTestService openFeignTestService;
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello() {
        helloService.hello();
        return "8087端口调用8088端口服务返回的值是："+openFeignTestService.hello();
    }
}
