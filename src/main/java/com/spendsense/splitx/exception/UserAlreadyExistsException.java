package com.spendsense.splitx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends Exception {
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
