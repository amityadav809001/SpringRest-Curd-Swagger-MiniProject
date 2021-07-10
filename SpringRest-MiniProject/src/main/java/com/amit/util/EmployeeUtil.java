package com.amit.util;

import org.springframework.stereotype.Component;

import com.amit.model.Employee;

@Component
public class EmployeeUtil {
	
	
	public  void calculateData(Employee e)
	{
		double sal=e.getEmpSal();
		double hra=sal * 20/100;
		double ta=sal * 10/100;
		
		e.setEmpHra(hra);
		e.setEmpTa(ta);
	}

}
