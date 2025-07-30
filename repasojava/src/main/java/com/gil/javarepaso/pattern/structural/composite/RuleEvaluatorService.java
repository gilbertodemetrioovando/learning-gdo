package com.gil.javarepaso.pattern.structural.composite;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleEvaluatorService {

	@Autowired
	private List<AbstractRuleEvaluator> ruleEvaluatorsAvailable;
	
	public void initEvaluateRules(String paymentmethod, String operationType) {
		System.out.println("###initEvaluateRules###\n\n\n");
		List<AbstractRuleEvaluator> evaluators = this.getEvaluator(paymentmethod, operationType);
		
		for (AbstractRuleEvaluator evaluator : evaluators) {
			evaluator.getRules();
			evaluator.evaluateRules(operationType);
		}

	}
	
	public List<AbstractRuleEvaluator> getEvaluator(String paymentmethod, String operationType) {
		
		return ruleEvaluatorsAvailable.stream()
		.filter(evaluator -> evaluator.getPaymentMethod().equalsIgnoreCase(paymentmethod) 
				&& evaluator.getOperationType().equals(operationType))
		.toList();
	}

}
