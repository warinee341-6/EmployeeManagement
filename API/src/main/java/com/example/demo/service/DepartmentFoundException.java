package com.example.demo.service;

public class DepartmentFoundException extends RuntimeException{
	
	public DepartmentFoundException(Long id) {
		super("Cannot found Department: "+id);
	}

}
