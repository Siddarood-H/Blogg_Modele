package com.bloggapp.exceptions;

public class SignatureException  extends RuntimeException
{
	public SignatureException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

}
