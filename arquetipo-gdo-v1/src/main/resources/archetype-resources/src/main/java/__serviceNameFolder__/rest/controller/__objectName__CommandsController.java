package ${package}.${objectNameFolder.replace('/','.')}.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ${package}.${objectNameFolder.replace('/','.')}.service.domain.${objectName};
import ${package}.${objectNameFolder.replace('/','.')}.service.${objectName}Service;
import ${package}.${objectNameFolder.replace('/','.')}.rest.dto.${objectName}DTO;

@RestController
@RequestMapping(value = "/${objectNameFolder}")
@Api(value = "${objectName}CommandsController", produces = "application/json")
/**
 * Controlador de commands 
 *
 * @author Jose Antonio Navarro janavarro.fuentes@atsistemas.com
 */
public class  ${objectName}CommandsController {

	@Autowired
	private ${objectName}Service ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Service;

    /**
     * Crea ${objectName}.
     *
     * @param ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} ${objectName}DTO
     * @param builder UriComponentsBuilder
     * @return ${objectName}DTO
     */
	@ApiOperation(value = "Crea un ${objectName}", tags = { "Controlador ${objectName}s" })
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "${objectName} creado", response = ${objectName}DTO.class), @ApiResponse(code = 404, message = "No creado") })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<${objectName}DTO> create(@ApiParam(value = "${objectName} que se va a crear", required = true) @RequestBody @Valid ${objectName}DTO ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}, UriComponentsBuilder builder) {

		${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Created = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Service.save(${objectName}.from${objectName}DTO(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}));

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("${objectName}/{id}")
				.buildAndExpand(String.valueOf(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Created.getId())).toUri());

		${objectName}DTO output = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Created.to${objectName}DTO();
		return new ResponseEntity<>(output, headers, HttpStatus.CREATED);
	}

    /**
     * Actualiza ${objectName}.
     *
     * @param ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)} ${objectName}DTO
     * @param builder UriComponentsBuilder
     * @return ${objectName}DTO
     */
	@ApiOperation(value = "Actualiza un ${objectName}", tags = { "Controlador ${objectName}s" })
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "${objectName} actualiza", response = ${objectName}DTO.class), @ApiResponse(code = 404, message = "No actualizado") })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<${objectName}DTO> update(@ApiParam(value = "${objectName} que se va a actualizar", required = true) @RequestBody @Valid ${objectName}DTO ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}, UriComponentsBuilder builder) {

		${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Created = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Service.save(${objectName}.from${objectName}DTO(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}));

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("${objectNameFolder}/{id}")
				.buildAndExpand(String.valueOf(${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Created.getId())).toUri());

		${objectName}DTO output = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Created.to${objectName}DTO();
		return new ResponseEntity<>(output, headers, HttpStatus.ACCEPTED);
	}
	#if ($type == "DELETE")
    /**
     * Borra ${objectName}.
     *
     * @param id Integer
     * @return ${objectName}DTO
     */
	@ApiOperation(value = "Elimina un ${objectName}", tags = { "Controlador ${objectName}s" })
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "${objectName} eliminado", response = ${objectName}DTO.class), @ApiResponse(code = 404, message = "No eliminado") })
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<${objectName}DTO> delete(@ApiParam(value = "Id del ${objectName} que se va a crear", required = true) @PathVariable String id) {

		${objectName} ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Deleted = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Service.delete(new Integer(id));

		HttpHeaders headers = new HttpHeaders();
		
		${objectName}DTO output = ${objectName.substring(0,1).toLowerCase()}${objectName.substring(1)}Deleted.to${objectName}DTO();
		return new ResponseEntity<>(output, headers, HttpStatus.ACCEPTED);
	}
	#end
}
