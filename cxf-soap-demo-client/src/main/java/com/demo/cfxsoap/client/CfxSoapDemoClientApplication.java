package com.demo.cfxsoap.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages= { "com.demo.cfxsoap.config", "com.demo.cfxsoap.soapservice"})
@SpringBootApplication(scanBasePackages= { "com.demo.cfxsoap"})
//@SpringBootApplication
public class CfxSoapDemoClientApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CfxSoapDemoClientApplication.class, args);
    }
}