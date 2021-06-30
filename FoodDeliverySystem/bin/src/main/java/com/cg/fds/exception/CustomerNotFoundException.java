/**
 * 
 */
package com.cg.fds.exception;

/**
 * @author advai
 *
 */
public class CustomerNotFoundException extends RuntimeException{
	//passing message to RuntimeException
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
