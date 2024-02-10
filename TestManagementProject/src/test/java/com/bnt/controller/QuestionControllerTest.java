package com.bnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.bnt.model.Categories;
import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;
import com.bnt.service.QuestionService;

class QuestionControllerTest {

	@Mock
	QuestionService questionServiceMock;

	@InjectMocks
	QuestionController questionController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	public Questions setAddQuestionRequest() {
		Categories category = new Categories();
		
		category.setTitle("Spring boot");
		
		Questions question = new Questions();
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
	void testAddQuestion() {
		Questions question = setAddQuestionRequest();
		when(questionServiceMock.addQuestionByName(question)).thenReturn(question);

		ResponseEntity<Questions> response = questionController.addQuestion(question);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(question, response.getBody());
	}

	@Test
	void testGetAllQuestions() {
		Questions question = setAddQuestionRequest();
		List<QuestionsResponse> questions = new ArrayList<>();
		questions.add(question.toResponse());
		when(questionServiceMock.getAllQuestions()).thenReturn(questions);
		List<QuestionsResponse> response = questionController.getAllQuestions();

		assertEquals(questions, response);
	}

	@Test
	void testGetQuestionById() {
		long questionId = 1;
		QuestionsResponse question = new QuestionsResponse();
		when(questionServiceMock.getQuestionsById(questionId)).thenReturn(question);

		ResponseEntity<QuestionsResponse> response = questionController.getQuestionById(questionId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(question, response.getBody());
	}
	
	@Test
    void testUpdateQuestion() {
        Questions updatedQuestion = setAddQuestionRequest();
        QuestionsResponse updatedQuestionResponse = new QuestionsResponse();
        when(questionServiceMock.updateQuestion(updatedQuestion)).thenReturn(updatedQuestionResponse);

        ResponseEntity<QuestionsResponse> response = questionController.updateQuestion(updatedQuestion);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedQuestionResponse, response.getBody());
    }

	@Test
	void testDeleteQuestion() {
		long questionId = 1;
		doNothing().when(questionServiceMock).deleteQuestion(questionId);

		ResponseEntity<Long> response = questionController.deleteCategory(questionId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(questionId, response.getBody());
	}

	@Test
	void testImportExcelToDatabase() throws IOException {
		String content = "Arrays in java are-";

		String filename = "test.xlsx";
		byte[] contentBytes = content.getBytes();
		MultipartFile multipartFile = new MockMultipartFile(filename, filename,
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", contentBytes);
		List<MultipartFile> files = new ArrayList<>();
		files.add(multipartFile);

		doNothing().when(questionServiceMock).importQuestionsFromExcel(files);

		ResponseEntity<String> response = questionController.importExcelToDatabase(files);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Added Successfully Questions Data", response.getBody());
	}

}
