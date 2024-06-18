package com.example.demochild1.controller;

import com.example.demochild1.Service.HelloService;
import com.example.demochild1.feign.OpenFeignTestService;
import jakarta.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Lock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RequestMapping
@RestController
public class OpenFeignTestController {
    @Autowired
    private OpenFeignTestService openFeignTestService;
    @Autowired
    private HelloService helloService;
    @Autowired
    RedissonClient redissonClient;
    @GetMapping("/hello")
    public String hello() {
        RLock lock = redissonClient.getLock("");
        lock.lock();
        helloService.hello();
        lock.unlock();
        return "8087端口调用8088端口服务返回的值是："+openFeignTestService.hello();
    }
}
