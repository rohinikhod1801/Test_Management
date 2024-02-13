package com.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TestManagementService testManagementService;

	@PostMapping
	public Employee register(@RequestBody Employee employee) {

		Employee employees = employeeService.register(employee);
		logger.info("Added New employee : {}", employee.toString());
		return employees;
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		logger.info("Getting All employees data : {}", employees.toString());
		return employees;
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			logger.info("Getting employee by ID: {}", employee.toString());
			return ResponseEntity.ok(employee);
		} catch (EmployeeNotFoundException ex) {
			logger.error("Employee not found with ID: {}", employeeId, ex);
			throw new EmployeeNotFoundException("Error occurred while getById employee" + ex);
		}
	}

	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeDetails) {
	    try {
	        Employee updatedEmployee = employeeService.updateEmployee(employeeDetails);
	        logger.info("Updated employee details: {}", updatedEmployee.toString());
	        return ResponseEntity.ok(updatedEmployee);
	    } catch (EmployeeNotFoundException ex) {
	        logger.error("Employee not found with ID: {}", employeeDetails.getEmployeeId(), ex);
	        throw new EmployeeNotFoundException("Error occurred while updating employee details: " + ex.getMessage());
	    }
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("employeeId") long employeeId) {
	    try {
	    	Employee deleteEmployee=employeeService.deleteEmployee(employeeId);
	        logger.info("Deleted employee with ID: {}", employeeId);
	        return ResponseEntity.ok(deleteEmployee);
	    } catch (EmployeeNotFoundException ex) {
	        logger.error("Employee not found with ID: {}", employeeId, ex);
	        throw new EmployeeNotFoundException("Error occurred while deleting employee details: " + ex.getMessage());
	    }
	}
	
	 @GetMapping("/employees/{id}/tests")
	    public Tests getTestForEmployee(@PathVariable Long id) {
	        // Logic to get test for employee from TestManagementService
	        return testManagementService.getTestForEmployee(id);
	    }
}
