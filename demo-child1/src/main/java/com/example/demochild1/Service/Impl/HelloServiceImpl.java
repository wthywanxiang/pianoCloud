package com.example.demochild1.Service.Impl;

import com.example.demochild1.Service.HelloService;

import java.time.LocalDateTime;

public class HelloServiceImpl implements HelloService {
    public String hello() throws Exception {
        if(LocalDateTime.now().equals(LocalDateTime.now()))throw new Exception();
        return "Hello ";
    }
}
