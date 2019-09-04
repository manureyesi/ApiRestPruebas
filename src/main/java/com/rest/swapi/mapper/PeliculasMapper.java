package com.rest.swapi.mapper;

import java.util.ArrayList;
import java.util.List;

import com.rest.apirest.response.PeliculasResponse;
import com.rest.swapi.vo.PeliculasVO;

public class PeliculasMapper {

	public static List<PeliculasVO> pasarDeRespuestaToVO (final List<PeliculasResponse> peliculas) {
		
		List<PeliculasVO> listaPeliculas = new ArrayList<>();
		
		for (PeliculasResponse peliculaAux: peliculas) {
			listaPeliculas.add(new PeliculasVO(
					peliculaAux.getTitle(),
					peliculaAux.getReleaseDate()));
		}
		return listaPeliculas;
	}
	
}
