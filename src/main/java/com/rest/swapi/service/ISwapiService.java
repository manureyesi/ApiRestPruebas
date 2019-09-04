package com.rest.swapi.service;

import com.rest.exception.ErrorPruebasException;
import com.rest.swapi.vo.DatosPersonajeVO;

public interface ISwapiService {

	/**
	 * Buscar Datos por nombre de personaje de Star Wars
	 *
	 * @param nombre
	 * @return DatosPersonajeVO
	 * @throws ErrorPruebasException
	 */
	public DatosPersonajeVO buscarPersonajePorNombre (String nombre) throws ErrorPruebasException;
	
}
