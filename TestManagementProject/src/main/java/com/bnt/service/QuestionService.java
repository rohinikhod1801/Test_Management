package com.bnt.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bnt.entity.Questions;
import com.bnt.entity.QuestionsResponse;

public interface QuestionService {
	
	public List<QuestionsResponse> getAllQuestions();
	
	public QuestionsResponse getQuestionsById(Long questionId);

	public QuestionsResponse updateQuestion(Questions questions);

	public void deleteQuestion(Long questionId);
	
	public Questions addQuestionByName(Questions question);

	public void importQuestionsFromExcel(List<MultipartFile> multipartfiles) throws IOException ;
	
}
