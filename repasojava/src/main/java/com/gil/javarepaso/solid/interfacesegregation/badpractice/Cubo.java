package com.gil.javarepaso.solid.interfacesegregation.badpractice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cubo implements ICalculadoraAreaForma {

	@Override
	public double calculateArea() {
		log.info("Area calculada");
		return 0;
	}

	@Override
	public double calculateVolume() {
		log.info("Volumen calculado");
		return 0;
	}
	
}
