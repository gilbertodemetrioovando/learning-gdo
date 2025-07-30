package com.gil.javarepaso.solid.interfacesegregation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cubo implements ICalculadoraArea, ICalculadoraVolumen{

	@Override
	public double calculateVolume() {
		log.info("Calculando volumen de cubo");
		return 0;
	}

	@Override
	public double calculateArea() {
		log.info("Calculando area de cubo");
		return 0;
	}

}
