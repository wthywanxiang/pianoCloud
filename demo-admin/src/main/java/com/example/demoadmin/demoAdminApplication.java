package com.example.demoadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.example.demoadmin","com.example.democommon"})
@EnableFeignClients(basePackages = "com.example.demoadmin.feign")
public class demoAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(demoAdminApplication.class, args);
    }
}
