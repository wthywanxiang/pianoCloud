package com.example.demochild1.Service.Impl;

import com.example.demochild1.Service.HelloService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class HelloServiceImpl implements HelloService {
    public String hello() {
        return "Hello ";
    }
}
