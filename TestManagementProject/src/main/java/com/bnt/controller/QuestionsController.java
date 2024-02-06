package com.bnt.controller;

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

import com.bnt.exception.CategoryNotFoundException;
import com.bnt.exception.QuestionNotFoundException;
import com.bnt.model.QuestionsRequest;
import com.bnt.model.QuestionsResponse;
import com.bnt.service.QuestionService;

@RestController
@RequestMapping("/questionController")
public class QuestionsController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionsController.class);

	@Autowired
	QuestionService service;
	
	@PostMapping("/{category_id}")
	public ResponseEntity<String> addQuestionToCategory(
			@PathVariable("category_id") Long category_id,
	        @RequestBody QuestionsRequest question) {
	    
	    try {
	    	if (category_id == null) {
                throw new QuestionNotFoundException("Category ID is required");
            }
            service.addQuestion(category_id, question);
            return ResponseEntity.ok("Question added to category successfully");
        } catch (QuestionNotFoundException e) {
        	throw new QuestionNotFoundException("Error occurred while adding new questions" + e);
        } 
	}
	
	@GetMapping("/questions")
	public List<QuestionsResponse> getAllQuestions() {	
		List<QuestionsResponse> questions= service.getAllQuestions();
		logger.info("Getting all Questions: {}",questions.toString());
		return questions;
	}
	
	@GetMapping("/questions/{questionId}")
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
	            @RequestBody QuestionsRequest updatedQuestionRequest) {
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
}
