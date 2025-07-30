package com.gil.javarepaso.solid.singleresponsibility;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Factura {
	private Libro libro;
	private int cantidad;
	private double tasaDescuento;
	private double tasaImpuesto;
	private double total;

	public Factura(int cantidad, double tasaDescuento, double tasaImpuesto) {
		this.cantidad = cantidad;
		this.tasaDescuento = tasaDescuento;
		this.tasaImpuesto = tasaImpuesto;
	}
}
