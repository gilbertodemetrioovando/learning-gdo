package com.gil.javarepaso.pattern.structural.composite;

import org.springframework.stereotype.Component;

@Component
public class ThreeDSRuleEvaluatorService extends EcommerceRuleEvaluatorService{

	@Override
	public Object evaluateRules(Object request) {
		System.out.println("Ejecutando ThreeDSRuleEvaluatorService.evaluateRules() de => paymentMethod: " + 
			this.getPaymentMethod() + ", operationType: " + this.getOperationType());
		return new Object();
	}

	@Override
	public Object getRules() {
		System.out.println("Ejecutando ThreeDSRuleEvaluatorService.getRules() de => paymentMethod: " + 
			this.getPaymentMethod() + ", operationType: " + this.getOperationType());
		return new Object();
	}
}
