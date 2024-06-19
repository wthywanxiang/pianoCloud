package com.example.demouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.example.demouser","com.example.democommon"})
@EnableFeignClients(basePackages = "com.example.demouser.feign")
public class demoUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(demoUserApplication.class, args);
    }
}
