package jp.co.axa.apidemo.entities;

import lombok.Getter;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jp.co.axa.apidemo.validation.Department;
import jp.co.axa.apidemo.validation.Salary;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

  	@Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    @NotEmpty(message = "Please provide a Employee Name")
    private String name;

   

	@Salary
	@Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
	@NotNull(message = "Please provide a Employee Salary")
    private Integer salary;

	
	@Department
    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    @NotEmpty(message = "Please provide a Employee Department")
    private String department;

}
