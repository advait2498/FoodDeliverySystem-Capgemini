package com.capgemini.fds.exception;

public class CustomerNotFoundException extends RuntimeException{
	//passing message to RuntimeException
	public CustomerNotFoundException(String message) {
		super(message);
	}
}