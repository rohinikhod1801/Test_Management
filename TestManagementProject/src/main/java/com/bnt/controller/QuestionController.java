package com.bnt.controller;

import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bnt.exception.CategoryNotFoundException;
import com.bnt.exception.QuestionNotFoundException;
import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;
import com.bnt.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	QuestionService service;
	
	@PostMapping("/{category_id}")
	public ResponseEntity<String> addQuestionToCategory(
			@PathVariable("category_id") Long category_id,
	        @RequestBody Questions question) {
	    
	    try {
	    	if (category_id == null) {
                throw new CategoryNotFoundException("Category ID is required");
            }
            service.addQuestion(category_id, question);
            return ResponseEntity.ok("Question added to category successfully");
        } catch (QuestionNotFoundException e) {
        	throw new QuestionNotFoundException("Error occurred while adding new questions" + e);
        } 
	}
	
	@GetMapping
	public List<QuestionsResponse> getAllQuestions() {	
		List<QuestionsResponse> questions= service.getAllQuestions();
		logger.info("Getting all Questions: {}",questions.toString());
		return questions;
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<QuestionsResponse> getQuestionById(@PathVariable("questionId") Long questionId) {
		try {
			QuestionsResponse response = service.getQuestionsById(questionId);
			logger.info("Getting category by ID: {}", questionId);
			return ResponseEntity.ok(response);
		} catch (QuestionNotFoundException ex) {
			logger.error("questions not found with ID: {}", questionId, ex);
			throw new QuestionNotFoundException("Error occurred while getById questions" + ex);
		}
	}

	 @PutMapping
	 public ResponseEntity<QuestionsResponse> updateQuestion(
	            @RequestBody Questions updatedQuestionRequest) {
	        try {
	        	QuestionsResponse updatedQuestion = service.updateQuestion(updatedQuestionRequest);
	            return ResponseEntity.ok(updatedQuestion);
	        } catch (QuestionNotFoundException e) {	      
	        	throw new QuestionNotFoundException("Error occurred while updating Questions" + e);
	        }
	    }
	 
	 @DeleteMapping("/{questionId}")
		public ResponseEntity<Long> deleteCategory(@PathVariable("questionId") Long questionId) {
			try {
				logger.info("Deleting question with ID: {}", questionId);
				service.deleteQuestion(questionId);
				return ResponseEntity.ok(questionId);
			} catch (QuestionNotFoundException ex) {
				logger.error("question not found with ID: {}", questionId, ex);
				throw new QuestionNotFoundException("Error occurred while deleting Questions" + ex);
			}

		}
	 
	 @PostMapping
	 public ResponseEntity<List<Questions>> importQuestions(@RequestParam("file") MultipartFile file) {
	        try {
	        	logger.info("Importing questions from Excel file");
	            InputStream excelInputStream = file.getInputStream();
	            List<Questions> importedQuestions = service.importQuestionsFromExcel(excelInputStream);
	            return ResponseEntity.ok(importedQuestions);
	        } catch (IOException e) {
	        	logger.error("Error importing questions from Excel file: {}", e.getMessage());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

}
