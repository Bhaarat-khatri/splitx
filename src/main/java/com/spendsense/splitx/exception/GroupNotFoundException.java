package com.spendsense.splitx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GroupNotFoundException extends RuntimeException {
		
	public GroupNotFoundException(String message) {
		super(message);
	}
}
