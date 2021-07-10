package com.amit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.amit.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Modifying
	@Query("update Employee set empName=:name where empId=:id")
	public void updateEmployeeName(String name,Integer id);

}
