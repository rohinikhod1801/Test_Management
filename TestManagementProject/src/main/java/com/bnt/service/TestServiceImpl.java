package com.bnt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.exception.TestIdNotExistException;
import com.bnt.model.TestRequest;
import com.bnt.model.TestResponse;
import com.bnt.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public TestRequest addTest(TestRequest test) {
        return testRepository.save(test);
    }

    @Override
    public List<TestResponse> getAllTest() {
        List<TestRequest> tests = testRepository.findAll();
        List<TestResponse> testList = new ArrayList<>();

        for (TestRequest test : tests) {
            testList.add(test.toResponse());
        }

        return testList;
    }

    @Override
    public TestResponse getTestById(Long id) {
        TestRequest findTestId = testRepository.findById(id).orElse(null);
        if (findTestId == null) {
	        throw new TestIdNotExistException("Question not found");
	    }
        return convertToCategoryResponse(findTestId);
    }

    @Override
    public TestResponse updateTest(TestRequest test) {
    		try {
    			TestRequest existingQuestion = testRepository.findById(test.getTestId())
    					.orElseThrow(() -> new TestIdNotExistException("Question not found"));
    			existingQuestion.setTitle(test.getTitle());
    			existingQuestion.setDescription(test.getDescription());
    			existingQuestion.setMaxMarks(test.getMaxMarks());
    			existingQuestion.isActive();
    			existingQuestion.setNumberOfQuestions(test.getNumberOfQuestions());
    			
    			TestRequest response = testRepository.save(existingQuestion);
    			return convertToCategoryResponse(response);
    		} catch (Exception e) {
    			throw new TestIdNotExistException("Failed to update question"+e);
    		}
    	}
    
    @Override
    public void deleteTest(Long testId) {
    	 if (testRepository.existsById(testId)) {
 	        try {
 	        	testRepository.deleteById(testId);
 	        } catch (TestIdNotExistException e) {
 	            throw new TestIdNotExistException("Failed to delete testId");
 	        }
 	    } else {
 	        throw new TestIdNotExistException("test with ID " + testId + " not found");
 	    }
    }
    
    private TestResponse convertToCategoryResponse(TestRequest test) {
    	TestResponse response = new TestResponse();
		response.setTitle(test.getTitle());
		response.setDescription(test.getDescription());
		response.setMaxMarks(test.getMaxMarks());
		response.isActive();
		response.setNumberofQuestions(test.getNumberOfQuestions());
		response.setQuestionIds(test.getQuestions());
		return response;
	}
}
