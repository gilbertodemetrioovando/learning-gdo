package com.demo.cfxsoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages= { "com.demo.cfxsoap.config", "com.demo.cfxsoap.soapservice"})
@SpringBootApplication(scanBasePackages= { "com.demo.cfxsoap"})
//@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}