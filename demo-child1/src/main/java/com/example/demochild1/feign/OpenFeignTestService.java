package com.example.demochild1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("demo-child2")
public interface OpenFeignTestService {
    @GetMapping("/hello")
    String hello(@RequestBody Map<String,String> params);
}
