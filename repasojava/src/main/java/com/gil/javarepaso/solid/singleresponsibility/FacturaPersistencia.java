package com.gil.javarepaso.solid.singleresponsibility;

public class FacturaPersistencia {

	Factura factura;

	public FacturaPersistencia(Factura factura) {
		this.factura = factura;
	}

	public void guardarArchivo(String nombreArchivo) {
		System.out.println("Generando archivo MOCK:" + nombreArchivo);
	}
}
