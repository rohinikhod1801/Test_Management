package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {
	
	public Employee register(Employee employee);
    public Employee login(String email, String password);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(long employeeId);
    public Employee updateEmployee(Employee employeeDetails);
    public Employee deleteEmployee(long employeeId);
    //public Employee assignTestToEmployee(long employeeId, long testId);
	/*
	 * public List<Tests> getAssignedTests(long employeeId); public List<Tests>
	 * getSelfEnrolledTests(long employeeId); public void takeTest(long employeeId,
	 * long testId); //TestResult getResult(long employeeId, long testId); public
	 * void assignRole(long employeeId, long roleId); public void removeRole(long
	 * employeeId, long roleId);
	 */

}
