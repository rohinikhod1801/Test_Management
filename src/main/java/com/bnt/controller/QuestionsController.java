package com.bnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.bnt.exception.CategoryException;
import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;
import com.bnt.service.QuestionService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/questionController")
public class QuestionsController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionsController.class);

	@Autowired
	QuestionService service;
	
	@PostMapping("/{category_id}")
	public ResponseEntity<Map<String, String>> addQuestionToCategory(
			@PathVariable("category_id") Long category_id,
	        @RequestBody Questions question) {
	    service.addQuestion(category_id, question);
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Question added to category successfully");
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/questions")
	public List<QuestionsResponse> getAllQuestions() {	
		List<QuestionsResponse> questions= service.getAllQuestions();
		logger.info("Getting all Questions: {}",questions.toString());
		return questions;
	}
	
	@GetMapping("/questions/{questionId}")
    public QuestionsResponse getQuestionById(@PathVariable("questionId") Long questionId) {
        return service.getQuestionsById(questionId);
    }
	
	 @PutMapping
	 public ResponseEntity<QuestionsResponse> updateQuestion(
	            @RequestBody Questions updatedQuestionRequest) {
	        try {
	        	QuestionsResponse updatedQuestion = service.updateQuestion(updatedQuestionRequest);
	            return ResponseEntity.ok(updatedQuestion);
	        } catch (EntityNotFoundException e) {	      
	            return ResponseEntity.notFound().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	 
	 @DeleteMapping("/{questionId}")
		public ResponseEntity<?> deleteCategory(@PathVariable("questionId") Long questionId) {
			try {
				logger.info("Deleting question with ID: {}", questionId);
				this.service.deleteQuestion(questionId);
				return ResponseEntity.ok().build();
			} catch (CategoryException ex) {
				logger.error("question not found with ID: {}", questionId, ex);
				return ResponseEntity.status(404).body(ex.getMessage());
			}

		}
}
