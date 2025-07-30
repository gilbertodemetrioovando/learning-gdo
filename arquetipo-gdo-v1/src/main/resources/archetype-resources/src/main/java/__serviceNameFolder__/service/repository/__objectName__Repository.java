package ${package}.${objectNameFolder.replace('/','.')}.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${package}.${objectNameFolder.replace('/','.')}.service.domain.${objectName};

/**
 * ${objectName}Repository Repositorio para el almacenamiento de {@link ${package}.${objectNameFolder.replace('/','.')}.service.domain.${objectName}} 
 *
 * @author Jose Antonio Navarro janavarro.fuentes@atsistemas.com
 *
 */
@Repository
public interface ${objectName}Repository  extends JpaRepository<${objectName}, Integer> 
{

}
