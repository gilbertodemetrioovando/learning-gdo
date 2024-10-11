package com.demo.cfxsoap.client.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cfxsoap.client.service.SoapServiceClient;
import com.demo.cfxsoap.soapservice.Charge;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/test-soap")
@AllArgsConstructor
public class TestSoapController {
	
	private final SoapServiceClient soapServiceClient;

	@GetMapping("/sayHello")
	public ResponseEntity<String> testSayHello(@RequestParam String value) {
		return ResponseEntity.ok(this.soapServiceClient.sayHello(value));
	}
	
	@GetMapping("/getCharges")
	public ResponseEntity<List<Charge>> getCharges() {
		return ResponseEntity.ok(this.soapServiceClient.getAllCharges());
	}
}
