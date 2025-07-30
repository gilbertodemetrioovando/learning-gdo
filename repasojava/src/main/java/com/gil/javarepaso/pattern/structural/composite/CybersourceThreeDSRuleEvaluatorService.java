package com.gil.javarepaso.pattern.structural.composite;

import org.springframework.stereotype.Component;

@Component
public class CybersourceThreeDSRuleEvaluatorService extends ThreeDSRuleEvaluatorService {

	@Override
	public Object evaluateRules(Object request) {
		System.out.println("Ejecutando CybersourceThreeDSRuleEvaluatorService.evaluateRules() de => paymentMethod: " + 
			this.getPaymentMethod() + ", operationType: " + this.getOperationType());
		return new Object();
	}

	@Override
	public Object getRules() {
		System.out.println("Ejecutando CybersourceThreeDSRuleEvaluatorService.getRules() de => paymentMethod: " + 
			this.getPaymentMethod() + ", operationType: " + this.getOperationType());
		return new Object();
	}

}
