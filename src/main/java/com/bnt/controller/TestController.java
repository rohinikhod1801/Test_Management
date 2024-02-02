package com.bnt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.exception.CategoryNotFoundException;
import com.bnt.exception.TestIdNotExistException;
import com.bnt.model.Test;
import com.bnt.service.TestService;

@RestController
@RequestMapping("/tests")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

		
	@Autowired
    private TestService testService;

	/*@PostMapping("/{category_id}")
	public Test createTest(@PathVariable("category_id") Long category_id,@RequestBody Test test) {
		  try {
		    	if (category_id == null) {
	                throw new CategoryNotFoundException("Category ID is required");
	            }
			Test addTest = testService.addTest(category_id,test);
			logger.info("addTest: Test added successfully with ID {}", test.getTest_id());
			return addTest;
		} catch (Exception e) {
			logger.error("Error adding test: {}", e.getMessage());
			throw new TestIdNotExistException("Error adding test:");
		}
	}*/
	
	
	 @PostMapping("/create")
	    public ResponseEntity<Test> createTest(@RequestBody Test testRequest) {
	        try {
	            Test createdTest = testService.addTest(testRequest);
	            return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	
	
	/*
	 * @PostMapping public ResponseEntity<Test> addTest(@RequestBody Test test) {
	 * try { // Your logic to save the test with questions
	 * 
	 * // Ensure questions are associated with the test for (Questions question :
	 * test.getQuestions()) { question.ge }
	 * 
	 * // Save the test Test savedTest = testRepository.save(test);
	 * 
	 * return new ResponseEntity<>(savedTest, HttpStatus.CREATED); } catch
	 * (Exception e) { return new ResponseEntity<>(null,
	 * HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */


}
