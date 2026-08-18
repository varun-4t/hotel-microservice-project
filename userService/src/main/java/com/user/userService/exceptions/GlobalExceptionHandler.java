package com.user.userService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import com.user.userService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFound ex){
	String msg = ex.getMessage();
	ApiResponse response = ApiResponse.builder().msg(msg).success(false).status(HttpStatus.NOT_FOUND).build();
	return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
}
}
