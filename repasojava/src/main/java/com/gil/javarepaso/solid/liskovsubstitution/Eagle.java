package com.gil.javarepaso.solid.liskovsubstitution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Eagle extends FlyingBird {

	@Override
	void fly() {
		log.info("Ave volando");
	}

}
