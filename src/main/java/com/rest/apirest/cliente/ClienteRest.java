package com.rest.apirest.cliente;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.exception.ErrorPruebasException;
import com.rest.exception.TipoError;

public class ClienteRest {

	private static final Logger LOG = Logger.getLogger(ClienteRest.class);
	
	/**
	 * Metodo que llama un webService peticion Get
	 * @param endpoint
	 * @return String
	 * @throws ErrorPruebasException
	 */
	public static String llamarServicioRestGet (final String endpoint) throws ErrorPruebasException {
		
		final String METHODNAME = "llamarServicioRestGet: ";
		LOG.info(METHODNAME+"Endpoint: " + endpoint);
				
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response = rt.exchange(endpoint, HttpMethod.GET,entity,String.class);
		
		//Comprobar si la respuesta es correcta
		if (HttpStatus.OK != response.getStatusCode()) {
			LOG.error(METHODNAME+"Error consulta");
			throw new ErrorPruebasException(TipoError.ERROR_LLAMADA_PETICION_REST);
		}
		
		return response.getBody();
		
	}
	
}
