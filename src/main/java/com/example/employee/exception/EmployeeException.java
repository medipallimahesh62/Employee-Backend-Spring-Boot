package com.example.employee.exception;

public class EmployeeException extends RuntimeException {
	
	static final long serialVersionUID = -3387516993124229948L;

	public EmployeeException() {
		super();
	}
	
	public EmployeeException(String message) {
		super(message);
	}
	
	public EmployeeException(String message, Throwable cause) {
		super(message, cause);
	}
}
