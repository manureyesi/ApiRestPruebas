package com.rest.swapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.apirest.cliente.ClienteRest;
import com.rest.apirest.response.BusquedaNombreUsuariosResponse;
import com.rest.apirest.response.DatosPersonajeResponse;
import com.rest.apirest.response.DatosVehiculoResponse;
import com.rest.apirest.response.PeliculasResponse;
import com.rest.apirest.response.PlanetaResponse;
import com.rest.exception.ErrorPruebasException;
import com.rest.exception.TipoError;
import com.rest.swapi.mapper.PeliculasMapper;
import com.rest.swapi.vo.DatosPersonajeVO;

@Service
public class SwapiServiceImpl implements ISwapiService {
	
	private static final Logger LOG = Logger.getLogger(SwapiServiceImpl.class);
	
	private static final String KEY_ENPOINT = "rest.endpoint";
	
	@Autowired
	private Environment enviroment;
	
	@Override
	public DatosPersonajeVO buscarPersonajePorNombre(final String nombre) throws ErrorPruebasException {
		
		final String METHODNAME = "buscarPersonajePorNombre: ";
		LOG.info(METHODNAME.concat("Buscando datos de: ").concat(nombre));
		
		//Buscar personaje por nombre
		DatosPersonajeResponse datos = buscarDatosDelPersonaje(nombre);
		
		//Comprobar si el nombre es exacto
		if (!nombre.equals(datos.getName())) {
			LOG.error("Error el nombre no concuerda");
			throw new ErrorPruebasException(TipoError.ERROR_LLAMADA_PETICION_REST);
		}
		
		//Buscar vehiculo mas rapido
		DatosVehiculoResponse datosVehiculo = buscarDatosDelVehiculo(datos.getVehicles());
		
		//Buscar Peliculas
		List<PeliculasResponse> listaPeliculas = buscarPeliculas(datos.getFilms());
		
		//Buscar Datos planeta
		PlanetaResponse datosPlaneta = buscarDatosPlaneta(datos.getHomeworld());
		
		return new DatosPersonajeVO(
				datos.getName(),
				datos.getBirthYear(),
				datos.getGender(),
				datosPlaneta.getName(),
				datosVehiculo!=null ? datosVehiculo.getName() : null,
				PeliculasMapper.pasarDeRespuestaToVO(listaPeliculas));
	}

	/**
	 * Metodo para buscar los datos del personaje
	 * @param nombre
	 * @return DatosPersonaje
	 * @throws ErrorPruebasException 
	 */
	private DatosPersonajeResponse buscarDatosDelPersonaje (final String nombre) throws ErrorPruebasException {
		
		final String METHODNAME = "buscarDatosDelPersonaje: ";
		LOG.info(METHODNAME.concat("Buscando datos de: ").concat(nombre));
				
		//Buscar personaje por nombre
		ObjectMapper mapper = new ObjectMapper();
		BusquedaNombreUsuariosResponse busqueda;
			try {
				//Buscar datos usuario
				busqueda = mapper.readValue(
						ClienteRest.llamarServicioRestGet(enviroment.getProperty(KEY_ENPOINT).concat(nombre)),
						BusquedaNombreUsuariosResponse.class);
			} catch (IOException | ErrorPruebasException e) {
				LOG.error("Error al buscar Personaje chamada");
				throw new ErrorPruebasException(e, TipoError.ERROR_LLAMADA_PETICION_REST);
			}
		
		LOG.debug(METHODNAME.concat(busqueda.toString()));
		
		//Comprobar resultado
		if (busqueda.getCount()!=1) {
			LOG.error("Error duplicado");
			throw new ErrorPruebasException(TipoError.ERROR_LLAMADA_PETICION_REST);
		}
		
		return busqueda.getResults().get(0);
		
	}
	
	/**
	 * Buscar vehiculo mas rapido
	 * @param listaUrl
	 * @return DatosVehiculoResponse
	 * @throws ErrorPruebasException 
	 */
	private DatosVehiculoResponse buscarDatosDelVehiculo (final List<String> listaUrl) throws ErrorPruebasException {
		
		final String METHODNAME = "buscarDatosDelPersonaje: ";
		LOG.info(METHODNAME.concat("Buscando datos del vehiculo mas rapido."));
		
		DatosVehiculoResponse datosVehiculo = null;
		
		if (!listaUrl.isEmpty()) {
		
			ObjectMapper mapper = new ObjectMapper();
			
			//Comprobar todos los coches
			for (String url: listaUrl) {
				
				//Buscar datos vehiculo
				DatosVehiculoResponse datosVehiculoAux = null;
				try {
					datosVehiculoAux = mapper.readValue(
							ClienteRest.llamarServicioRestGet(url),
							DatosVehiculoResponse.class);
				} catch (IOException e) {
					LOG.error("Error al buscar Vehiculo con url: ".concat(url));
				}
				
				//Comprobar velocidad
				if (datosVehiculoAux != null && 
					(datosVehiculo == null ||
					Integer.valueOf(datosVehiculo.getMaxSpeed())>Integer.valueOf(datosVehiculoAux.getMaxSpeed()))) {
					datosVehiculo = datosVehiculoAux;
				}
				
			}
			
			LOG.debug(METHODNAME.concat("El vehiculo mas rapido es: ").concat(datosVehiculo.toString()));
			
		}
		
		return datosVehiculo;
		
	}
	
	/**
	 * Buscar peliculas
	 * @param listaUrl
	 * @return List<PeliculasResponse>
	 * @throws ErrorPruebasException 
	 */
	private List<PeliculasResponse> buscarPeliculas (final List<String> listaUrl) throws ErrorPruebasException {
		
		final String METHODNAME = "buscarPeliculas: ";
		LOG.info(METHODNAME.concat("Buscando datos de las peliculas en las que participo"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<PeliculasResponse> listaPeliculas = new ArrayList<>();
		
		//Buscar todas las peliculas
		for (String url: listaUrl) {
			
			//Buscar datos peliculas
			try {
				listaPeliculas.add(mapper.readValue(
						ClienteRest.llamarServicioRestGet(url),
						PeliculasResponse.class));
			} catch (IOException e) {
				LOG.error("Error al buscar Pelicula con url: ".concat(url));
			}
						
		}
		
		return listaPeliculas;
		
	}
	
	/**
	 * Buscar datos planeta
	 * @param url
	 * @return PlanetaResponse
	 * @throws ErrorPruebasException 
	 */
	private PlanetaResponse buscarDatosPlaneta (final String url) throws ErrorPruebasException {
		
		final String METHODNAME = "buscarDatosPlaneta: ";
		LOG.info(METHODNAME.concat("Buscando datos del planeta"));
		
		PlanetaResponse planeta = null;
		ObjectMapper mapper = new ObjectMapper();
		
		//Buscar datos planeta
		try {
			planeta = mapper.readValue(
					ClienteRest.llamarServicioRestGet(url),
					PlanetaResponse.class);
		} catch (IOException e) {
			LOG.error("Error al buscar Pelicula con url: ".concat(url));
		}
				
		return planeta;
		
	}
	
}
