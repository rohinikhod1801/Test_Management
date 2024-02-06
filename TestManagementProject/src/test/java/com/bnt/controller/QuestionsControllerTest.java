package com.bnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.bnt.exception.QuestionNotFoundException;
import com.bnt.model.CategoryRequest;
import com.bnt.model.QuestionsRequest;
import com.bnt.model.QuestionsResponse;
import com.bnt.service.QuestionService;

@SpringBootTest
class QuestionsControllerTest {

	@Mock
	private QuestionService questionService;

	@InjectMocks
	private QuestionsController questionsController;

	public QuestionsRequest setAddQuestionRequest() {
		CategoryRequest category = new CategoryRequest();
		category.setCategoryId(2l);
		category.setTitle("Spring boot");
		category.setDescription("this is spring boot Category");
		QuestionsRequest question = new QuestionsRequest();
		question.setQuestionId(1l);
		question.setContent("what is java");
		question.setOption1("depedent language");
		question.setOption2("only one system used");
		question.setOption3("hard to understand");
		question.setOption4("Independant easy to understand");
		question.setAnswer("Independant easy to understand");
		question.setCategory(category);
		return question;

	}

	@Test
	public void testAddQuestionToCategory_Success() {
		Long categoryId = 1L;
		QuestionsRequest question = setAddQuestionRequest();
		ResponseEntity<String> expectedResponse = ResponseEntity.ok("Question added to category successfully");
		ResponseEntity<String> actualResponse = questionsController.addQuestionToCategory(categoryId, question);
		assertEquals(expectedResponse, actualResponse);

	}

	@Test
	public void testAddQuestionToCategory_CategoryNotFound() {

		Long categoryId = 1L;
		QuestionsRequest question = new QuestionsRequest();
		questionsController.addQuestionToCategory(categoryId, question);
	}

	@Test
	public void testAddQuestionToCategory_QuestionNotFound() {

		Long categoryId = null;
		QuestionsRequest question = setAddQuestionRequest();
		assertThrows(QuestionNotFoundException.class, () -> {
			questionsController.addQuestionToCategory(categoryId, question);
		});

	}

	@Test
	public void testGetAllQuestions() {

		List<QuestionsResponse> expectedQuestions = Arrays.asList(new QuestionsResponse(), new QuestionsResponse());
		when(questionService.getAllQuestions()).thenReturn(expectedQuestions);
		List<QuestionsResponse> actualQuestions = questionsController.getAllQuestions();
		assertNotNull(actualQuestions);
		assertEquals(expectedQuestions, actualQuestions);
	}

	@Test
	public void testGetQuestionById_Success() {

		Long questionId = 1L;
		QuestionsResponse expectedResponse = new QuestionsResponse();

		when(questionService.getQuestionsById(questionId)).thenReturn(expectedResponse);
		ResponseEntity<QuestionsResponse> actualResponse = questionsController.getQuestionById(questionId);
		assertEquals(ResponseEntity.ok(expectedResponse), actualResponse);

	}

	@Test
	public void testGetQuestionById_QuestionNotFound() {

		Long questionId = 1L;

		try {
			when(questionService.getQuestionsById(questionId))
					.thenThrow(new QuestionNotFoundException("Question not found"));
			questionsController.getQuestionById(questionId);
		} catch (QuestionNotFoundException e) {

		}
	}

	@Test
	public void testUpdateQuestion_Success() {

		QuestionsRequest updatedQuestionRequest = new QuestionsRequest();
		QuestionsResponse expectedResponse = new QuestionsResponse();
		when(questionService.updateQuestion(updatedQuestionRequest)).thenReturn(expectedResponse);
		ResponseEntity<QuestionsResponse> actualResponse = questionsController.updateQuestion(updatedQuestionRequest);
		assertEquals(ResponseEntity.ok(expectedResponse), actualResponse);
	}

	@Test
	public void testDeleteQuestion_Success() {

		Long questionId = 1L;
		ResponseEntity<Long> actualResponse = questionsController.deleteCategory(questionId);
		assertEquals(ResponseEntity.ok(questionId), actualResponse);
	}

}
