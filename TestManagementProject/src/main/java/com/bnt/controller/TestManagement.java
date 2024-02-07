package com.bnt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.exception.TestIdNotExistException;
import com.bnt.model.TestRequest;
import com.bnt.model.TestResponse;
import com.bnt.service.TestServiceImpl;

@RestController
@RequestMapping("/tests")
public class TestManagement {

	private static final Logger logger = LoggerFactory.getLogger(TestManagement.class);

	@Autowired
	private TestServiceImpl testService;

	@PostMapping
	public ResponseEntity<TestRequest> createTest(@RequestBody TestRequest test) {
		TestRequest createdTest = testService.addTest(test);
		return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<TestResponse>> getAllTests() {
		List<TestResponse> tests = testService.getAllTest();
		return new ResponseEntity<>(tests, HttpStatus.OK);
	}

	@GetMapping("/{test_id}")
	public ResponseEntity<TestResponse> getTestById(@PathVariable("test_id") Long id) {
		try {
			TestResponse test = testService.getTestById(id);
			logger.info("Getting Test by ID: {}", test);
			return ResponseEntity.ok(test);
		} catch (TestIdNotExistException ex) {
			logger.error("Test not found with ID: {}", id, ex);
			throw new TestIdNotExistException("Error occurred while getById Test" + ex);
		}
	}

	@PutMapping
	public ResponseEntity<TestResponse> updateTest(@RequestBody TestRequest test) {
		try {
			TestResponse updatedTest = testService.updateTest(test);
			return new ResponseEntity<>(updatedTest, HttpStatus.OK);
		} catch (TestIdNotExistException e) {
			logger.error("Error occurred while updating Test", e);
			throw new TestIdNotExistException("Error occurred while updating Test" + e);
		}
	}

	@DeleteMapping("/{testId}")
	public ResponseEntity<String> deleteTest(@PathVariable("testId") Long testId) {
		try {
			this.testService.deleteTest(testId);
			logger.info("Getting Test by ID: {}", testId);
			return ResponseEntity.ok().build();
		} catch (TestIdNotExistException ex) {
			logger.error("Test not found with ID: {}", testId, ex);
			throw new TestIdNotExistException("Error occurred while Deleting Test" + ex);
		}
	}
}
