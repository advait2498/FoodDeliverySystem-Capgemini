/**
 * 
 */
package com.cg.fds.exception;

/**
 * @author advai
 *
 */
public class ItemNotFoundInCart extends RuntimeException{
	
	public ItemNotFoundInCart(String message) {
		super(message);
	}
}
