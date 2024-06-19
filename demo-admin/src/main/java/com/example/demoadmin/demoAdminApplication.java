package com.example.demoadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demoadmin","com.example.democommon"})
public class demoAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(demoAdminApplication.class, args);
    }
}
