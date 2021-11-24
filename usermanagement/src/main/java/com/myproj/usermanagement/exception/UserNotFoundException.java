package com.myproj.usermanagement.exception;

public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException()
	{
		super("User not found with given id!");
	}
}
