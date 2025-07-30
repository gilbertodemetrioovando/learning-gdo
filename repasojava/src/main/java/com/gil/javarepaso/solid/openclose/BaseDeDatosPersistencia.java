package com.gil.javarepaso.solid.openclose;

import com.gil.javarepaso.solid.singleresponsibility.Factura;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseDeDatosPersistencia implements Persistencia {

	@Override
	public void guardar(Factura factura) {
		log.info("Persistiendo factura en base de datos: {}", factura);
	}

}
