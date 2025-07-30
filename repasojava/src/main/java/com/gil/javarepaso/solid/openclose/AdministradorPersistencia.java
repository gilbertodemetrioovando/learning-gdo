package com.gil.javarepaso.solid.openclose;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdministradorPersistencia {

	private Persistencia persistencia;


	public static void main(String args[]) {
		AdministradorPersistencia admin = new AdministradorPersistencia(new BaseDeDatosPersistencia());
		admin = new AdministradorPersistencia();
		admin.setPersistencia(new ArchivoPersistencia());
	}
}
