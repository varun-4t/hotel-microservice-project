package com.user.userService.exceptions;

public class ResourceNotFound extends Exception{
	public ResourceNotFound(){
		super("Resource not found");
	}
	public ResourceNotFound(String msg){
		super(msg);
	}
}