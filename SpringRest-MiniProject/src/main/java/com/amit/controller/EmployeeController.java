package com.amit.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.exception.EmployeeNotFooundException;
import com.amit.model.Employee;
import com.amit.response.MessageResponse;
import com.amit.service.IEmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/employee")
@Api(description = "Employee Operation")
@Profile(value = { "default", "qa" })
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	// 1. create one Employee
	@PostMapping("/save")
	@ApiOperation(value = "Create Employees")
	public ResponseEntity<String> createEmployee(@RequestBody @Valid Employee employee) {
		Integer id = service.saveEmployee(employee);
		String message = "Employee '" + id + "' Created!";
		return new ResponseEntity<String>(message, HttpStatus.CREATED); // 201
	}

	// 2.delete one Employee
	@DeleteMapping("/remove/{id}")
	// @ApiIgnore
	@ApiOperation(value = "Delete One Employee")
	public ResponseEntity<String> deleteOneEmployee(@PathVariable Integer id) {

		ResponseEntity<String> response = null;
		try {
			service.deleteEmployee(id);
			String message = "Employee '" + id + "' Deleted";
			System.out.println("test");
			response = new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (EmployeeNotFooundException e) {

			throw e;

		}
		return response;

	}

	// 3. Get All employees
	@GetMapping("/all")
	@ApiOperation(value = "All Employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = service.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	// 4. Get one Employee
	@GetMapping("/one/{id}")
	@ApiOperation(value = "One Employee")
	public ResponseEntity<?> getOneEmployee(@PathVariable Integer id) {

		ResponseEntity<?> response = null;
		try {
			Employee employee = service.getOneEmployee(id);
			response = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}

		catch (EmployeeNotFooundException enfe) {

			throw enfe;

		}
		return response;
	}

	// 4. update Employee
	@PutMapping("/update")
	@ApiOperation(value = "Update Employee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {

		ResponseEntity<String> response = null;

		try {
			service.updateEmployee(employee);
			String message = "Employee  Updated Successfully";

			response = new ResponseEntity<String>(message, HttpStatus.OK);

		} catch (EmployeeNotFooundException e) {

			throw e;

		}

		return response;// 200
	}
	/*
	 * @PatchMapping("/update/{id}/{name}") public ResponseEntity<String>
	 * updateEmployeeName(@PathVariable Integer id, @PathVariable String name) {
	 * ResponseEntity<String> response = null; try {
	 * service.updateEmployeeName(name, id); String message =
	 * "Employee Name updated!"; response = new ResponseEntity<String>(message,
	 * HttpStatus.OK);
	 * 
	 * } catch (EmployeeNotFooundException e) { throw e; } m return response; }
	 */

	@PatchMapping("/update/{id}/{name}")
	public MessageResponse updateEmployeeName(@PathVariable Integer id, @PathVariable String name) {
		MessageResponse response = null;
		try {
			service.updateEmployeeName(name, id);
			String message = "Employee Name updated!";
			/*
			 * response = new MessageResponse( "Success", message, "EMPLOYEE", new
			 * Date().toString());
			 */
			
			response=MessageResponse
					.builder()
					.date(new Date().toString())
					.message(message)
					.status("success")
					.module("EMPLOYEE")
					.build();
		} catch (EmployeeNotFooundException e) {
			throw e;
		}

		return response;
	}
}
