package com.amit.exception;

public class EmployeeNotFooundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	     public EmployeeNotFooundException()
	     {
	    	 super();
	     }
	
	

	     public EmployeeNotFooundException(String message)
	     {
	    	 super(message);
	     }
	

}
