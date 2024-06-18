package com.example.demochild2.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(@RequestBody Map<String,String> params){
        return params.get("number");
    }
}
