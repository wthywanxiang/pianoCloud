package com.example.demouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demouser","com.example.democommon"})
public class demoUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(demoUserApplication.class, args);
    }
}
