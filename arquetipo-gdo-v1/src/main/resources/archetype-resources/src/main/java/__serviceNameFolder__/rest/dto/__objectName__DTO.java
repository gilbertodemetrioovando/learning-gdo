package ${package}.${objectNameFolder.replace('/','.')}.rest.dto;

import io.swagger.annotations.ApiModel;

/**
 * Data Transfer Object para el {@link ${package}.${objectNameFolder.replace('/','.')}.service.domain.${objectName}} del modelo de dominio
 *
 * @author Jose Antonio Navarro janavarro.fuentes@atsistemas.com
 */
@ApiModel(value = "Informacion de un ${objectName}", description = "Datos del ${objectName}")
public class ${objectName}DTO 
{
	private int id;
	private String descripcion;

	/**
	 * Constructor de ${objectName}DTO sin argumentos
	 */
	public ${objectName}DTO () {
		
	}
	/**
	 * Constructor de ${objectName}DTO con todos los atributos como argumentos
	 * 
	 * @param id the id to set
	 * @param descripcion the descripcion to set
	 * 
	 */
	public ${objectName}DTO (int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonaDTO [id=" + id + ", descripcion=" + descripcion + "]";
	}
}
