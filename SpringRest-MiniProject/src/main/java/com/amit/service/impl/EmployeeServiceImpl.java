package com.amit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amit.exception.EmployeeNotFooundException;
import com.amit.model.Employee;
import com.amit.repo.EmployeeRepository;
import com.amit.service.IEmployeeService;
import com.amit.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository erepo;

	@Autowired
	private EmployeeUtil util;

	public Integer saveEmployee(Employee e) {

		util.calculateData(e);
		e = erepo.save(e);

		return e.getEmpId();
	}

	public void updateEmployee(Employee e) {

		if (e.getEmpId() != null && erepo.existsById(e.getEmpId())) {
			util.calculateData(e);
			erepo.save(e);
		} else {
			throw new EmployeeNotFooundException(
					e.getEmpId() == null ? "No Id is Provided!" : "Employee '" + e.getEmpId() + "' not exist");
		}

	}

	public void deleteEmployee(Integer id) {

		erepo.delete(getOneEmployee(id));

	}

	public Employee getOneEmployee(Integer id) {

		Optional<Employee> opt = erepo.findById(id);

		if (opt.isPresent())
			return opt.get();
		else
			throw new EmployeeNotFooundException("Employee '" + id + "' not exist");

	}

	public List<Employee> getAllEmployees() {

		return erepo.findAll();
	}

	@Transactional
	public void updateEmployeeName(String name, Integer id) {
    	
    	if(erepo.existsById(id))
    		erepo.updateEmployeeName(name, id);
    	else throw new EmployeeNotFooundException("Employee '" + id + "' not exist");
    		  
 
		
		
	}

}
