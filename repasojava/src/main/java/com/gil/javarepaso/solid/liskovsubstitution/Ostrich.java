package com.gil.javarepaso.solid.liskovsubstitution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ostrich extends NonFlyingBird {

	@Override
	void move() {
		log.info("Ave moviendose");
	}

}
