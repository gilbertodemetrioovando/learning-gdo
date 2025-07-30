package com.gil.javarepaso.pattern.structural.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gil.javarepaso.pattern.structural.composite.RuleEvaluatorService;

@RestController
@RequestMapping("/test")
public class TestCompositeController {
	@Autowired
	private RuleEvaluatorService ruleEvaluatorService;

	@GetMapping("/evaluateRules")
	public void executeEvaluator(@RequestParam String paymentMethod, 
			@RequestParam String operationType) {
		this.ruleEvaluatorService.initEvaluateRules(paymentMethod, operationType);
	}
	
	/**
	 * test controller => http://localhost:8080/test/evaluateRules?paymentMethod=CARD&operationType=CARD_PRESENT
	 * 
	 */
}
