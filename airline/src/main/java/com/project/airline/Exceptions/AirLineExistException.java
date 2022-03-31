package com.project.airline.Exceptions;

public class AirLineExistException extends Exception{

	
	public AirLineExistException(String name) {
		super("There is already Airline with the name: " + name);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
