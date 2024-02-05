package com.bnt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.exception.QuestionNotFoundException;
import com.bnt.model.Category;
import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;
import com.bnt.repository.CategoryRepository;
import com.bnt.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final CategoryRepository repository;
	private final QuestionRepository questionRepository;

	@Autowired
	public QuestionServiceImpl(CategoryRepository repository, QuestionRepository questionRepository) {
		this.repository = repository;
		this.questionRepository = questionRepository;
	}

	@Override
	public void addQuestion(Long categoryId, Questions question) {
		Category category = repository.findById(categoryId)
				.orElseThrow(() -> new QuestionNotFoundException("Category not found"));
		question.setCategory(category);
		questionRepository.save(question);
	}

	@Override
	public List<QuestionsResponse> getAllQuestions() {
	    List<Questions> questions = questionRepository.findAll();
	    List<QuestionsResponse> questionsResponses = new ArrayList<>();

	    for (Questions question : questions) {
	        questionsResponses.add(question.toResponse());
	    }

	    return questionsResponses;
	}

	@Override
	public QuestionsResponse getQuestionsById(Long questionId) {
	    Questions question = questionRepository.findById(questionId).orElse(null);

	    if (question == null) {
	        throw new QuestionNotFoundException("Question not found");
	    }

	    return convertToCategoryResponse(question);
	}


	@Override
	public QuestionsResponse updateQuestion(Questions request) {
		try {
			Questions existingQuestion = questionRepository.findById(request.getQuestionId())
					.orElseThrow(() -> new QuestionNotFoundException("Question not found"));
			existingQuestion.setQuestionId(request.getQuestionId());
			existingQuestion.setContent(request.getContent());
			existingQuestion.setOption1(request.getOption1());
			existingQuestion.setOption2(request.getOption2());
			existingQuestion.setOption3(request.getOption3());
			existingQuestion.setOption4(request.getOption4());
			existingQuestion.setAnswer(request.getAnswer());
			existingQuestion.setMarks(request.getMarks());

			Questions response = questionRepository.save(existingQuestion);
			return convertToCategoryResponse(response);
		} catch (Exception e) {
			throw new QuestionNotFoundException("Failed to update question"+e);
		}
	}

	@Override
	public void deleteQuestion(Long questionId) {
		 if (!questionRepository.existsById(questionId)) {
	            throw new QuestionNotFoundException("Question not found with id: " + questionId);
	        }
	        questionRepository.deleteById(questionId);
	    }

	private QuestionsResponse convertToCategoryResponse(Questions questions) {
		QuestionsResponse response = new QuestionsResponse();
		response.setContent(questions.getContent());
		response.setOption1(questions.getOption1());
		response.setOption2(questions.getOption2());
		response.setOption3(questions.getOption3());
		response.setOption4(questions.getOption4());
		response.setAnswer(questions.getAnswer());
		response.setMarks(questions.getMarks());
		return response;
	}

}
