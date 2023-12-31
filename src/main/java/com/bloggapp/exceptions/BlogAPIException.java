package com.bloggapp.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;

public class BlogAPIException extends RuntimeException {
	
	
	 String message;
	
	public BlogAPIException( String messsage) {
		
	
		
		this.message = messsage;
	}
	public BlogAPIException(HttpStatus notFound, String messsage) {

	}
	


	public String getMesssage() {
		return message;
	}
	public void setMesssage(String messsage) {
		this.message = messsage;
	}

	}


