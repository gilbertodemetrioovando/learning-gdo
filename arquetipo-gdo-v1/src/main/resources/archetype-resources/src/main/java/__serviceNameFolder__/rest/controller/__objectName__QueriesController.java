package ${package}.${objectNameFolder.replace('/','.')}.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ${package}.${objectNameFolder.replace('/','.')}.service.domain.${objectName};
import ${package}.${objectNameFolder.replace('/','.')}.service.${objectName}Service;
import ${package}.${objectNameFolder.replace('/','.')}.rest.dto.${objectName}DTO;

@RestController
@RequestMapping(value = "/${objectNameFolder}")
@Api(value = "${objectName}QueriesController", produces = "application/json")
/**
 * Controlador de queries 
 *
 * @author Jose Antonio Navarro janavarro.fuentes@atsistemas.com
 */
public class ${objectName}QueriesController {

	@Autowired	
	private ${objectName}Service ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Service;

	/**
	 * Pide todos los ${objectName}s.
	 *
	 * @return los ${objectName}s
	 */
	@ApiOperation(value = "Recupera ${objectName}s", tags = { "Controlador ${objectName}s" })
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "${objectName}s obtenidos", response = ${objectName}DTO.class), @ApiResponse(code = 404, message = "No encontrados") })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<${objectName}DTO> getAll() {
		List<${objectName}DTO> ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}s = new ArrayList<>();
		for (${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} : ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Service.getAll()) {
			${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}s.add(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}.to${objectName}DTO());
		}
		return ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}s;
	}

	/**
	 * Pide un ${objectName} por id.
	 *
	 * @param id int id del ${objectName}
	 * @return el ${objectName}
	 */
	@ApiOperation(value = "Recupera ${objectName}s", tags = { "Controlador ${objectName}s" })
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "${objectName} recuperado", response = ${objectName}DTO.class), @ApiResponse(code = 404, message = "No encontrado") })
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	@ResponseBody
	public ${objectName}DTO getById(@ApiParam(value = "Id del ${objectName} que se pide", required = true) @PathVariable String id) {
		${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Service.getById(new Integer(id));
		return ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}.to${objectName}DTO();
	}
}
