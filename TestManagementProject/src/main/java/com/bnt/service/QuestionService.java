package com.bnt.service;

import java.util.List;

import com.bnt.model.QuestionsRequest;
import com.bnt.model.QuestionsResponse;

public interface QuestionService {

	public void addQuestion(Long questionId, QuestionsRequest question);
	
	public List<QuestionsResponse> getAllQuestions();
	
	public QuestionsResponse getQuestionsById(Long questionId);

	public QuestionsResponse updateQuestion(QuestionsRequest questions);

	public void deleteQuestion(Long questionId);

	
}
