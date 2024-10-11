package com.demo.cfxsoap.soapservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.demo.cfxsoap.annotation.WebServiceEndpoint;
import com.demo.cfxsoap.model.Charge;

@WebServiceEndpoint("/SoapService1")  
@WebService
public class SoapService {

	@WebMethod
	public String sayHello(String name) {
		return "Hello, " + name + "!";
	}

	@WebMethod
	public List<Charge> getAllCharges() {

		List<Charge> cars = List.of(
				new Charge(1, "Charge 1", 300f), 
				new Charge(2, "Charge 2", 140f),
				new Charge(2, "Charge 3", 300f), 
				new Charge(4, "Charge 4", 90f));

		//return cars.stream().filter((e) -> e.getBrand().equalsIgnoreCase(brandName)).collect(Collectors.toList());
		return cars;

	}
}
