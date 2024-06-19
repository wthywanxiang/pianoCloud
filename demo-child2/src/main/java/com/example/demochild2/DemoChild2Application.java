package com.example.demochild2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demochild2","com.example.democommon"})
public class DemoChild2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoChild2Application.class, args);
    }

}
