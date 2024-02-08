package com.bnt.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;

public interface QuestionService {

	public void addQuestion(Long questionId, Questions question);
	
	public List<QuestionsResponse> getAllQuestions();
	
	public QuestionsResponse getQuestionsById(Long questionId);

	public QuestionsResponse updateQuestion(Questions questions);

	public void deleteQuestion(Long questionId);

	List<Questions> importQuestionsFromExcel(InputStream excelInputStream) throws IOException;	
	
	public Questions addQuestionByName(Questions question);

	
}
