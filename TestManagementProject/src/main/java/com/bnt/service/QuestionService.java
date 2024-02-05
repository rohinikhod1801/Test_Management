package com.bnt.service;

import java.util.List;

import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;

public interface QuestionService {

	public void addQuestion(Long questionId, Questions question);
	
	public List<QuestionsResponse> getAllQuestions();
	
	public QuestionsResponse getQuestionsById(Long questionId);

	public QuestionsResponse updateQuestion(Questions questions);

	public void deleteQuestion(Long questionId);

	
}
