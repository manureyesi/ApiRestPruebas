package com.rest.exception;

public enum TipoError {

	OK(0, "OK"),
	ERROR(1, "Error Generico"),
	ERROR_LLAMADA_PETICION_REST(2, "Error Peticion Rest");

	private final Integer codigo;
	private final String descripcion;
	
	private TipoError(final Integer codigo, final String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	@Override
	public String toString() {
		return codigo + ": " + descripcion;
	}
	
}

