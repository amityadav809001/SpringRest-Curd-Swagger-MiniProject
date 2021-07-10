package com.amit.service;

import java.util.List;

import com.amit.model.Employee;

public interface IEmployeeService {

	Integer saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(Integer id);

	Employee getOneEmployee(Integer id);

	List<Employee> getAllEmployees();

	void updateEmployeeName(String name, Integer id);

}
