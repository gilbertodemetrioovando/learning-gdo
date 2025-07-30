package com.gil.javarepaso.pattern.structural.composite;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EcommerceRuleEvaluatorService extends AbstractRuleEvaluator {

	private String paymentMethod;
	private String operationType;
	
	@Override
	String getPaymentMethod() {
		return "CARD";
	}
	@Override
	String getOperationType() {
		return "ECOMMERCE";
	}

	@Override
	public Object evaluateRules(Object request) {
		super.evaluateRules(request);

		return new Object();
	}
}
