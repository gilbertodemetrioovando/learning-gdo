package ${package}.${objectNameFolder.replace('/','.')}.service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import ${package}.${objectNameFolder.replace('/','.')}.rest.dto.${objectName}DTO;

/**
 * Clase ${objectName} del dominio tiene su correspondiente {@link ${objectName}DTO}
 *
 * @author Jose Antonio Navarro janavarro.fuentes@atsistemas.com
 */
@Entity
public class ${objectName} 
{
	@Id
	private int id;
	private String descripcion;

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
	
	/**
	 * Metodo estatico para obtener un ${objectName} a partir de un ${objectName}DTO origen
	 * 
	 * @param ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} ${objectName}DTO origen
	 * 
	 * @return ${objectName}
	 */
	public static ${objectName} from${objectName}DTO(${objectName}DTO ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}) {
		${objectName} rest = new ${objectName}();
		rest.setId(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}.getId());
		rest.setDescripcion(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}.getDescripcion());
		return rest;
	}
	
	/**
	 * Metodo para obtener un ${objectName}DTO a partir de un ${objectName} origen
	 * 
	 * @return ${objectName}DTO
	 */
	public ${objectName}DTO to${objectName}DTO() {
		 ${objectName}DTO dto = new  ${objectName}DTO();
		 dto.setId(this.getId());
		 dto.setDescripcion(this.getDescripcion());
		return dto;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [id=" + id + ", descripcion=" + descripcion + "]";
	}
}
