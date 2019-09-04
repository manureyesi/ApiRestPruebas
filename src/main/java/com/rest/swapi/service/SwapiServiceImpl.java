package com.rest.swapi.service;

import org.springframework.stereotype.Service;

import com.rest.exception.ErrorPruebasException;
import com.rest.swapi.vo.DatosPersonajeVO;

@Service
public class SwapiServiceImpl implements ISwapiService {

	@Override
	public DatosPersonajeVO buscarPersonajePorNombre(final String nombre) throws ErrorPruebasException {
		
		return new DatosPersonajeVO("dssds", "dssds", "dssds", "dssds", "dssds", null);
		
	}

}
