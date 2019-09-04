package com.rest.exception;

public class ErrorPruebasException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7087174307754627007L;
	
	private final TipoError tipoError;

	public ErrorPruebasException(final TipoError tipoError){
        this.tipoError = tipoError;
    }
		
	public ErrorPruebasException(final String message, final TipoError tipoError){
        super(message);
        this.tipoError = tipoError;
    }

	public ErrorPruebasException(final Exception e, final TipoError tipoError){
        super(e);
        this.tipoError = tipoError;
    }

	public ErrorPruebasException(final String message, final Exception e, final TipoError codError){
        super(message, e);
        this.tipoError = codError;
    }
	
	public int getCodigoError() {
		return this.tipoError.getCodigo();
	}

	public String getDescripcionError() {
		return this.tipoError.getDescripcion();
	}
	
}
