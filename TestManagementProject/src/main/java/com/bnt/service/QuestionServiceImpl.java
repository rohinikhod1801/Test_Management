package com.bnt.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.bnt.exception.CategoryNotFoundException;
import com.bnt.exception.QuestionNotFoundException;
import com.bnt.model.Categories;
import com.bnt.model.Questions;
import com.bnt.model.QuestionsResponse;
import com.bnt.repository.CategoryRepository;
import com.bnt.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final CategoryRepository repository;
	private final QuestionRepository questionRepository;

	public QuestionServiceImpl(CategoryRepository repository, QuestionRepository questionRepository) {
		this.repository = repository;
		this.questionRepository = questionRepository;
	}

	@Override
	public Questions addQuestionByName(Questions question) {

		Categories category = repository.findByTitle(question.getCategory().getTitle())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));

		Categories categeries = new Categories();
		categeries.setCategoryId(category.getCategoryId());

		Questions questionRequest = new Questions();
		questionRequest.setContent(question.getContent());
		questionRequest.setOption1(question.getOption1());
		questionRequest.setOption2(question.getOption2());
		questionRequest.setOption3(question.getOption3());
		questionRequest.setOption4(question.getOption4());
		questionRequest.setAnswer(question.getAnswer());
		questionRequest.setMarks(question.getMarks());
		questionRequest.setCategory(categeries);
		return questionRepository.save(questionRequest);
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
			throw new QuestionNotFoundException("Failed to update question" + e);
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
		response.setQuestionId(questions.getQuestionId());
		response.setContent(questions.getContent());
		response.setOption1(questions.getOption1());
		response.setOption2(questions.getOption2());
		response.setOption3(questions.getOption3());
		response.setOption4(questions.getOption4());
		response.setAnswer(questions.getAnswer());
		response.setMarks(questions.getMarks());
		return response;
	}

	@Override
	public List<Questions> importQuestionsFromExcel(InputStream excelInputStream) throws IOException {
		Workbook workbook = WorkbookFactory.create(excelInputStream);
		List<Questions> importedQuestions = new ArrayList<>();

		Sheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			Questions question = new Questions();
			question.setContent(getCellValue(row.getCell(0)));
			question.setOption1(getCellValue(row.getCell(1)));
			question.setOption2(getCellValue(row.getCell(2)));
			question.setOption3(getCellValue(row.getCell(3)));
			question.setOption4(getCellValue(row.getCell(4)));
			question.setAnswer(getCellValue(row.getCell(5)));
			question.setMarks(getCellValue(row.getCell(6)));

			importedQuestions.add(questionRepository.save(question));
		}
		return importedQuestions;
	}

	private String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}

}
