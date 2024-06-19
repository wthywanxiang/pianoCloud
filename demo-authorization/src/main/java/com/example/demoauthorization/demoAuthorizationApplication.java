package com.example.demoauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demoauthorization","com.example.democommon"})
public class demoAuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(demoAuthorizationApplication.class, args);
    }
}
