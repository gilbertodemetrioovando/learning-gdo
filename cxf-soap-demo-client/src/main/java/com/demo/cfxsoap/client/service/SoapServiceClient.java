package com.demo.cfxsoap.client.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.demo.cfxsoap.soapservice.Charge;
import com.demo.cfxsoap.soapservice.SoapService;
import com.demo.cfxsoap.soapservice.SoapServiceService;

@Service
public class SoapServiceClient {
	
	private final SoapService soapService1 = new SoapServiceService().getSoapServicePort();

	
	public String sayHello(String value) {
		return this.soapService1.sayHello("Gil");
	}

	
	public List<Charge> getAllCharges() {
		return this.soapService1.getAllCharges();
	}


}
