package com.gil.javarepaso.solid.openclose;

import com.gil.javarepaso.solid.singleresponsibility.Factura;

public interface Persistencia {

	void guardar(Factura factura);

}
