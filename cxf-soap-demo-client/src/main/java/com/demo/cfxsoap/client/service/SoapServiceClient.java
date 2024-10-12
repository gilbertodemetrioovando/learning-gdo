package com.demo.cfxsoap.client.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.demo.cfxsoap.soapservice.Charge;
import com.demo.cfxsoap.soapservice.SoapService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SoapServiceClient {
	
	private final SoapService soapClientService;

	
	public String sayHello(String value) {
		return this.soapClientService.sayHello("Gil");
	}

	
	public List<Charge> getAllCharges() {
		return this.soapClientService.getAllCharges();
	}


}
