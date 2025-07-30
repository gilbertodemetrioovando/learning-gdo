package com.gil.javarepaso.solid.interfacesegregation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cuadrado implements ICalculadoraArea {@Override
	public double calculateArea() {
		log.info("Calculando area de cuadrado");
		return 0;
	}

}
