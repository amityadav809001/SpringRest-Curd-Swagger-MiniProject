package com.amit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "emptab")
public class Employee {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eid")
	private Integer empId;
	
	@Column(name = "ename")
	@NotNull(message ="Employee can not be null")
	private String empName;
	
	@Column(name = "edept")
	@NotNull(message = "dept can be null")
	private String empDept;
	@Column(name = "esal")
	private Double empSal;
	@Column(name = "ehra")
	private Double empHra;
	@Column(name = "eta")
	private Double empTa;
	
	
	
	

}
