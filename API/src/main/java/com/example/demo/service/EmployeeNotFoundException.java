package com.example.demo.service;

public class EmployeeNotFoundException extends RuntimeException{
	
	public EmployeeNotFoundException(Long id) {
		super("Cannot found employee: "+id);
	}

}
