package com.rest.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.exception.ErrorPruebasException;
import com.rest.swapi.service.ISwapiService;
import com.rest.vo.ResultadoVO;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StarWarsController {

	private static final Logger LOG = Logger.getLogger(StarWarsController.class);
	
	@Autowired
	private ISwapiService swapiService;
	
	@ApiOperation(
        value = "Consulta Star Wars",
        notes = "Consulta Star Wars"
    )
	@RequestMapping(value = "/swapi-proxy/person-info", method = RequestMethod.GET)
	public ResponseEntity<Object> crearToken (
			@RequestParam(name = "name", required=true) String nombre) {
			
		LOG.info("[GET] /swapi-proxy/person-info?name=".concat(nombre));
		
		ResponseEntity<Object> resposta = null;
		
		try {
			resposta = new ResponseEntity<>(
					swapiService.buscarPersonajePorNombre(nombre),
					HttpStatus.OK);
		} catch (ErrorPruebasException e) {
			LOG.error(e.getDescripcionError(), e);
			resposta = new ResponseEntity<Object>(new ResultadoVO(e.getDescripcionError()), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error("Error Generico", e);
			resposta = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return resposta;
	}
	
}
