package com.gil.javarepaso.solid.liskovsubstitution.badpractice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ostrich extends Bird{

	@Override
	void fly() {
		log.error("Ave no puede volar");
	}

}
