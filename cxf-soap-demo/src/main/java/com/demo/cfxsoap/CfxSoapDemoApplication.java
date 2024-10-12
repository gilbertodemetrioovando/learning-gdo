package com.demo.cfxsoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages= { "com.demo.cfxsoap.config", "com.demo.cfxsoap.soapservice"})
@SpringBootApplication(scanBasePackages= { "com.demo.cfxsoap"})
//@SpringBootApplication
public class CfxSoapDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CfxSoapDemoApplication.class, args);
    }
}