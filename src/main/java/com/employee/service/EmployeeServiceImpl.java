package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.exception.DuplicateEmployeeException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

	@Override
    public Employee register(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new DuplicateEmployeeException("Employee with email '" + employee.getEmail() + "' already exists");
        }
        return employeeRepository.save(employee);
    }

	@Override
	public Employee login(String email, String password) {
		
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

	    Employee getEmployee = new Employee();
	    getEmployee.setEmployeeId(employee.getEmployeeId());
	    getEmployee.setFirstName(employee.getFirstName());
	    getEmployee.setLastName(employee.getLastName());
	    getEmployee.setEmail(employee.getEmail());
	    getEmployee.setPassword(employee.getPassword());
	    
	    return getEmployee;
	}
	
	@Override
	public Employee updateEmployee(Employee employeeDetails) {
	    Employee employee = employeeRepository.findById(employeeDetails.getEmployeeId())
	            .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeDetails.getEmployeeId()));

	    employee.setFirstName(employeeDetails.getFirstName());
	    employee.setLastName(employeeDetails.getLastName());
	    employee.setEmail(employeeDetails.getEmail());
	    employee.setPassword(employeeDetails.getPassword());

	    return employeeRepository.save(employee);
	}

	@Override
	public Employee deleteEmployee(long employeeId) {
		 Employee employee = employeeRepository.findById(employeeId)
	                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
	        employeeRepository.delete(employee);
			return employee;
	    }
}
