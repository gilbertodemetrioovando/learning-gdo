package com.gil.javarepaso.solid.singleresponsibility;

public class FacturaImpresion {

	private Factura factura;

	public FacturaImpresion(Factura factura) {
		this.factura = factura;
	}

	public void imprimir() {
		System.out.println(factura.getCantidad() + "x " + factura.getLibro().getNombre() + " "
				+ factura.getLibro().getPrecio() + " $");
		System.out.println("Tasa de Descuento: " + factura.getTasaDescuento());
		System.out.println("Tasa de Impuesto: " + factura.getTasaImpuesto());
		System.out.println("Total: " + factura.getTotal() + " $");
	}

}
