package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class DuplicateEmployeeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668785795595097141L;
	
	public DuplicateEmployeeException(String message) {
        super(message);
    }

}