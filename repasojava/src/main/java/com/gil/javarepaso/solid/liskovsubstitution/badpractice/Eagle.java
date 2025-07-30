package com.gil.javarepaso.solid.liskovsubstitution.badpractice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Eagle extends Bird {

	@Override
	void fly() {
		log.info("Eagle flying");
	}

}
