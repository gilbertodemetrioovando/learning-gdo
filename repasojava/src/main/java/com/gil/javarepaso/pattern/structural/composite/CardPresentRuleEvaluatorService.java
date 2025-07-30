package com.gil.javarepaso.pattern.structural.composite;

import org.springframework.stereotype.Component;

@Component
public class CardPresentRuleEvaluatorService extends AbstractRuleEvaluator {

	@Override
	String getPaymentMethod() {
		return "CARD";
	}
	@Override
	String getOperationType() {
		return "CARD_PRESENT";
	}

	@Override
	public Object evaluateRules(Object request) {
		System.out.println("Ejecutando CardPresentRuleEvaluatorService.evaluateRules() de => paymentMethod: " + 
			this.getPaymentMethod() + ", operationType: " + this.getOperationType());
		return new Object();
	}
}
