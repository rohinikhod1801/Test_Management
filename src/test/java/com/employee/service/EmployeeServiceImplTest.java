package com.employee.service;

import com.employee.entity.Employee;
import com.employee.exception.DuplicateEmployeeException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	private Employee setEmployeeData() {
		Employee employee = new Employee();
		employee.setEmployeeId(1L);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setEmail("john.doe@example.com");
		employee.setPassword("password123");
		return employee;
	}

	@Test
	void registerNewEmployee() {

		Employee newEmployee = setEmployeeData();

		when(employeeRepository.findByEmail(newEmployee.getEmail())).thenReturn(Optional.empty());
		when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);
		Employee registeredEmployee = employeeService.register(newEmployee);
		assertNotNull(registeredEmployee);
		assertEquals(newEmployee, registeredEmployee);
	}

	@Test
	void register_DuplicateEmployee_ExceptionThrown() {
		Employee existingEmployee = new Employee();
		existingEmployee.setEmail("john.doe@example.com");
		when(employeeRepository.findByEmail(existingEmployee.getEmail())).thenReturn(Optional.of(existingEmployee));
		assertThrows(DuplicateEmployeeException.class, () -> employeeService.register(existingEmployee));
	}

	@Test
	void getEmployeeByIdExistingEmployee() {
		Employee employee = setEmployeeData();
		when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		Employee result = employeeService.getEmployeeById(employee.getEmployeeId());
		assertEquals(employee.getEmployeeId(), result.getEmployeeId());
	}

	@Test
	void getEmployeeByIdNonExistingEmployee() {
		long nonExistentEmployeeId = 100L;
		when(employeeRepository.findById(nonExistentEmployeeId)).thenReturn(Optional.empty());
		assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(nonExistentEmployeeId));
	}

	@Test
	void updateEmployeeExistingEmployee() {
		Employee employeeToUpdate = setEmployeeData();
		when(employeeRepository.findById(employeeToUpdate.getEmployeeId())).thenReturn(Optional.of(employeeToUpdate));
		when(employeeRepository.save(employeeToUpdate)).thenReturn(employeeToUpdate);
		Employee updatedEmployee = employeeService.updateEmployee(employeeToUpdate);
		assertEquals(employeeToUpdate.getEmployeeId(), updatedEmployee.getEmployeeId());
	}

	@Test
	void updateEmployeeNonExistingEmployee() {
		Employee nonExistentEmployee = setEmployeeData();
		when(employeeRepository.findById(nonExistentEmployee.getEmployeeId())).thenReturn(Optional.empty());
		assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(nonExistentEmployee));
	}

	@Test
	void deleteEmployeeExistingEmployee() {
		long employeeId = 1L;
		Employee employee = setEmployeeData();
		when(employeeRepository.findById(employeeId)).thenReturn(java.util.Optional.of(employee));
		Employee deletedEmployee = employeeService.deleteEmployee(employeeId);
		assertEquals(employeeId, deletedEmployee.getEmployeeId());
	}
}
