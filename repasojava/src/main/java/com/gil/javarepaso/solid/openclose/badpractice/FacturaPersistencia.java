package com.gil.javarepaso.solid.openclose.badpractice;

import com.gil.javarepaso.solid.singleresponsibility.Factura;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FacturaPersistencia {

	Factura factura;

	public FacturaPersistencia(Factura factura) {
		this.factura = factura;
	}

	public void guardarArchivo(String nombreArchivo) {
		log.info("guardarArchivo...: {}", nombreArchivo);
	}

	public void guardarEnBaseDatos() {
		log.info("guardarEnBaseDatos...");
	}
}
