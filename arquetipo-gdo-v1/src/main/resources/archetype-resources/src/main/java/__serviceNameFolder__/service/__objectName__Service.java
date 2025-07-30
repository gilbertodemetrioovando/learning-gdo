package ${package}.${objectNameFolder.replace('/','.')}.service;

import java.util.List;

import ${package}.${objectNameFolder.replace('/','.')}.service.domain.${objectName};

/**
 * ${objectName} Service, define el caso de uso del API
 *
 * @author Jose Antonio Navarro janavarro.fuentes@atsistemas.com
 *
 */
public interface ${objectName}Service 
{
	List<${objectName}> getAll();

	${objectName} save(${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)});

	${objectName} update(${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)});

	${objectName} delete(Integer id);

	${objectName} getById(Integer id);
}
