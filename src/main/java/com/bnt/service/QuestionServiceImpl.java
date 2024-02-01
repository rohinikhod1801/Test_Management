package com.bnt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.model.Category;
import com.bnt.model.CategoryResponse;
import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;
import com.bnt.repository.CategoryRepository;
import com.bnt.repository.QuestionRepository;

import jakarta.persistence.EntityNotFoundException;

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
        Category category = repository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        question.setCategory(category);
        questionRepository.save(question);
    }

    @Override
    public List<QuestionsResponse> getAllQuestions() {
        List<Questions> questions = questionRepository.findAll();
        return questions.stream()
                .map(Questions::toResponse)
                .collect(Collectors.toList());
    }

	@Override
	public QuestionsResponse getQuestionsById(Long questionId) {
		Questions question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        return question.toResponse();
	}

	@Override
	public QuestionsResponse updateQuestion(Long questionId,Questions questions) {
		try {
		 Questions existingQuestion = questionRepository.findById(questionId)
                 .orElseThrow(() -> new EntityNotFoundException("Question not found"));
		
		 Questions response= questionRepository.save(existingQuestion);	 
         return convertToCategoryResponse(response);
     } catch (Exception e) {
         throw new RuntimeException("Failed to update question", e);
     }
	}

	@Override
	public void deleteQuestion(Long questionId) {
		 try {
		        this.repository.deleteById(questionId);
		    } catch (Exception e) {
		        throw new RuntimeException("Failed to delete category", e);
		    }
	}

	private QuestionsResponse convertToCategoryResponse(Questions questions) {
		 QuestionsResponse response=new QuestionsResponse();
         // Update the fields of existingQuestion with the values from the updatedQuestionRequest
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
