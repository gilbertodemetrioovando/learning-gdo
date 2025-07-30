package com.gil.javarepaso.pattern.structural.composite;

public abstract class AbstractRuleEvaluator implements IRuleEvaluator {

	@Override
	public Object evaluateRules(Object request) {
		System.out.println("Ejecutando AbstractRuleEvaluator.evaluateRules() de => paymentMethod: " + 
			this.getPaymentMethod() + ", operationType: " + this.getOperationType());
		return new Object();
	}

	@Override
	public Object getRules() {
		System.out.println("Ejecutando AbstractRuleEvaluator.getRules() de => paymentMethod: " + 
			this.getPaymentMethod() + ", operationType: " + this.getOperationType());
		return new Object();
	}

	abstract String getPaymentMethod();
	abstract String getOperationType();

}
