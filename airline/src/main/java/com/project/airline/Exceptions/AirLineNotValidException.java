package com.project.airline.Exceptions;

public class AirLineNotValidException extends Exception{

	
	public AirLineNotValidException() {
		super("One or more of the required parameters for creating an airline is missing.");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
