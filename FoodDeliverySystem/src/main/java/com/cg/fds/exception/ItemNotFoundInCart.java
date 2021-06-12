package com.capgemini.fds.exception;

public class ItemNotFoundInCart extends RuntimeException{
	
	public ItemNotFoundInCart(String message) {
		super(message);
	}
}
