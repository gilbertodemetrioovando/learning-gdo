package ${package}.${objectNameFolder.replace('/','.')}.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.${objectNameFolder.replace('/','.')}.service.domain.${objectName};
import ${package}.${objectNameFolder.replace('/','.')}.service.repository.${objectName}Repository;
import ${package}.${objectNameFolder.replace('/','.')}.service.${objectName}Service;

/**
 * Implementacion interna de {@link ${package}.${objectNameFolder.replace('/','.')}.service.${objectName}Service}. Esta clase no se debe acceder directamente
 *
 * @author Jose Antonio Navarro janavarro.fuentes@atsistemas.com
 */
@Service
public class ${objectName}ServiceImpl implements ${objectName}Service 
{
	@Autowired
	private ${objectName}Repository ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Repository;

	@Override
	public List<${objectName}> getAll() {
		List<${objectName}> ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}s = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Repository.findAll();

		return ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}s;
	}


	@Override
	public ${objectName} save(${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}) {
		${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Repository.save(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)});

		return ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)};

	}

	@Override
	public ${objectName} update(${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}) {
		${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Repository.save(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)});

		return ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)};

	}

	@Override
	public ${objectName} delete(Integer id) {
		${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} = getById(id);

		List<${objectName} > ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}List = new ArrayList<>();

		${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}List.add(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)});
		${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Repository.deleteInBatch(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}List);

		return ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)};
	}
	@Override
	public ${objectName} getById(Integer id) {
		${objectName} domain = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Repository.findOne(id);
		if (domain == null) {
			return null;// not found
		}
		return domain;
	}
}
