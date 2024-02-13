package com.employee.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeService;

class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	public Employee addEmployeeData() {

		Employee employee = new Employee();
		employee.setEmployeeId(1l);
		employee.setFirstName("mari");
		employee.setLastName("Doe");
		employee.setEmail("mari.doe@example.com");
		employee.setPassword("password123");
		return employee;

	}

	@Test
	public void testRegister() {
		Employee employee = addEmployeeData();

		when(employeeService.register(employee)).thenReturn(employee);

		Employee registeredEmployee = employeeController.register(employee);

		assertEquals(employee, registeredEmployee);
	}

	@Test
	public void testGetAllEmployees() {
		Employee employee = addEmployeeData();
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
		when(employeeService.getAllEmployees()).thenReturn(employeeList);

		List<Employee> retrievedEmployees = employeeController.getAllEmployees();

		assertEquals(employeeList, retrievedEmployees);
	}

	@Test
	public void testGetEmployeeById() {
		Long employeeId = 1L;
		Employee employee = addEmployeeData();

		when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);

		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById(employeeId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(employee, responseEntity.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		Employee employee = addEmployeeData();

		Employee updateEmployee = new Employee();
		employee.setEmployeeId(1l);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setEmail("mari.doe@example.com");
		employee.setPassword("password123");

		when(employeeService.updateEmployee(employee)).thenReturn(updateEmployee);

		ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(employee);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(updateEmployee, responseEntity.getBody());
	}

	@Test
	public void testDeleteEmployee() {
		long employeeId = 1L;

		ResponseEntity<Employee> responseEntity = employeeController.deleteEmployee(employeeId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void getEmployeeById_NotFound() {
		when(employeeService.getEmployeeById(1L)).thenThrow(new EmployeeNotFoundException("Employee not found"));
		assertThrows(EmployeeNotFoundException.class, () -> employeeController.getEmployeeById(1L));
	}

	@Test
	public void updateEmployee_NotFound() {
		Employee nonExistentEmployee = addEmployeeData();

		doThrow(new EmployeeNotFoundException("Employee not found")).when(employeeService)
				.updateEmployee(any(Employee.class));
		assertThrows(EmployeeNotFoundException.class, () -> {
			employeeController.updateEmployee(nonExistentEmployee);
		});
	}

	@Test
	void deleteEmployee_NotFound() {

		long nonExistentEmployeeId = 100L;
		doThrow(new EmployeeNotFoundException("Employee not found")).when(employeeService)
				.deleteEmployee(nonExistentEmployeeId);
		assertThrows(EmployeeNotFoundException.class, () -> {
			employeeController.deleteEmployee(nonExistentEmployeeId);
		});
	}

}
