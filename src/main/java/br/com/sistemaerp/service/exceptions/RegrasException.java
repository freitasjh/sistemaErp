package br.com.sistemaerp.service.exceptions;

public class RegrasException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegrasException(String msg) {
		super(msg);
	}

	public RegrasException(String msg, Throwable cause) {
		super(msg, cause);
	}

}