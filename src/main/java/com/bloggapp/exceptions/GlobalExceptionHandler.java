package com.bloggapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bloggapp.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler<ExpiredJwtException> {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse (message, true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>methodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String ,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName=( (FieldError) error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ApiResponse>BlogAPIException(BlogAPIException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse (message, true);

		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ApiResponse>SignatureException(SignatureException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse (message, true);

		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	
	}
	

	

}
