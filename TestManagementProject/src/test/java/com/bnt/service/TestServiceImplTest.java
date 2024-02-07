package com.bnt.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.exception.TestIdNotExistException;
import com.bnt.model.Tests;
import com.bnt.model.TestResponse;
import com.bnt.repository.TestRepository;

@SpringBootTest
class TestServiceImplTest {

	@Mock
	private TestRepository testRepository;

	@InjectMocks
	private TestServiceImpl testService;

	public Tests addTestData() {
		Tests test = new Tests();
		test.setTestId(1L);
		test.setTitle("Spring Boot");
		test.setDescription("created new Spring Boot Test");
		test.setNumberOfQuestions(10);
		test.setMaxMarks(50);
		test.setActive(false);
		return test;
	}

	@Test
	public void testAddTest() {

		Tests test = addTestData();
		when(testRepository.save(test)).thenReturn(test);
		Tests result = testService.addTest(test);
		assertNotNull(result);
		assertEquals(test, result);
	}

	@Test
	public void testGetAllTest() {
		Tests request = addTestData();
		List<Tests> testList = new ArrayList<>();
		testList.add(request);
		when(testRepository.findAll()).thenReturn(testList);
		List<TestResponse> result = testService.getAllTest();
		assertEquals(testList.size(), result.size());
	}

	@Test
	public void testGetTestById() {

		Long testId = 1L;
		Tests test = new Tests();
		test.setTestId(testId);
		when(testRepository.findById(testId)).thenReturn(Optional.of(test));
		TestResponse result = testService.getTestById(testId);
		assertNotNull(result);
		assertEquals(testId, 1l);

	}

	@Test
	public void testGetTestById_NotFound() {

		Long testId = 1L;
		when(testRepository.findById(testId)).thenReturn(Optional.empty());
		assertThrows(TestIdNotExistException.class, () -> {
			testService.getTestById(testId);
		});
	}

	@Test
	public void testUpdateTest() {

		Long testId = 1L;
		Tests request = new Tests();
		request.setTestId(testId);
		request.setTitle("Updated Title");
		request.setDescription("Updated Description");
		request.setMaxMarks(100);
		request.setNumberOfQuestions(20);
		Tests existingTest = new Tests();
		existingTest.setTestId(testId);
		existingTest.setTitle("Old Title");
		existingTest.setDescription("Old Description");
		existingTest.setMaxMarks(50);
		existingTest.setNumberOfQuestions(10);
		when(testRepository.findById(testId)).thenReturn(Optional.of(existingTest));
		when(testRepository.save(any())).thenReturn(request);
		TestResponse result = testService.updateTest(request);
		assertEquals(testId, 1l);
		assertEquals("Updated Title", result.getTitle());
		assertEquals("Updated Description", result.getDescription());
		assertEquals(100, result.getMaxMarks());
		assertEquals(20, result.getNumberofQuestions());

	}

	@Test
	public void testDeleteTest() {

		Long testId = 1L;
		when(testRepository.existsById(testId)).thenReturn(true);
		assertDoesNotThrow(() -> {
			testService.deleteTest(testId);
		});

	}

	@Test
	public void testDeleteTest_NotFound() {

		Long testId = 1L;
		when(testRepository.existsById(testId)).thenReturn(false);
		assertThrows(TestIdNotExistException.class, () -> {
			testService.deleteTest(testId);
		});
	}

}
