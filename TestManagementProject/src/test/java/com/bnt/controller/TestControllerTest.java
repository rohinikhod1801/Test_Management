package com.bnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.exception.TestIdNotExistException;
import com.bnt.model.TestRequest;
import com.bnt.model.TestResponse;
import com.bnt.service.TestServiceImpl;

@SpringBootTest
class TestControllerTest {

	@Mock
	private TestServiceImpl testService;

	@InjectMocks
	private TestController testController;
	
	public TestRequest addTestData() {
		TestRequest test = new TestRequest();
		test.setTestId(1L);
		test.setTitle("Spring Boot");
		test.setDescription("created new Spring Boot Test");
		test.setNumberOfQuestions(10);
		test.setMaxMarks(50);
		test.setActive(false);
		return test;
	}
	
	
	@Test
	public void testCreateTest() {
		TestRequest test=addTestData();		
		when(testService.addTest(test)).thenReturn(test);
		ResponseEntity<TestRequest> responseEntity = testController.createTest(test);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(test, responseEntity.getBody());
	}

	@Test
	public void testGetAllTests() {
		TestRequest test = addTestData();
	    List<TestResponse> testList = new ArrayList<>(); 
	    testList.add(test.toResponse()); 
	    when(testService.getAllTest()).thenReturn(testList);
	    ResponseEntity<List<TestResponse>> responseEntity = testController.getAllTests();
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals(testList, responseEntity.getBody());
	}

	@Test
	public void testGetTestById_Exists() {		
		Long testId = 1L;
		TestResponse testResponse = new TestResponse();
		testResponse.setTitle("Spring Boot");
		testResponse.setDescription("created new Spring Boot Test");
		testResponse.setNumberofQuestions(10);
		testResponse.setMaxMarks(50);
		testResponse.setActive(false);
		when(testService.getTestById(testId)).thenReturn(testResponse);
		ResponseEntity<TestResponse> responseEntity = testController.getTestById(testId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(testResponse, responseEntity.getBody());
	}

	@Test
	public void testGetTestById_NotExists() {		
		Long testId = 1L;
		 when(testService.getTestById(testId)).thenThrow(new TestIdNotExistException("Test not found"));
		assertThrows(TestIdNotExistException.class, () -> testController.getTestById(testId));
	}

	@Test
	public void testUpdateTest() {	
		TestRequest test =addTestData();
		when(testService.updateTest(test)).thenReturn(new TestResponse());
		ResponseEntity<TestResponse> responseEntity = testController.updateTest(test);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	@Test
	public void testUpdateTest_NotExists() {	   
	    TestRequest test = addTestData();
	    when(testService.updateTest(test)).thenThrow(new TestIdNotExistException("Test not found"));
	    assertThrows(TestIdNotExistException.class, () -> testController.updateTest(test));
	}

	@Test
	public void testDeleteTest_Exists() {
		Long testId = 1L;
		ResponseEntity<String> responseEntity = testController.deleteTest(testId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNull(responseEntity.getBody());
	}

	@Test
	public void testDeleteTest_NotExists() {	
		Long testId = 1L;
	    doThrow(new TestIdNotExistException("Test not found")).when(testService).deleteTest(testId);
	    assertThrows(TestIdNotExistException.class, () -> testController.deleteTest(testId));
	}

}
