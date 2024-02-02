package com.bnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.model.Test;
import com.bnt.repository.CategoryRepository;
import com.bnt.repository.QuestionRepository;
import com.bnt.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
    private TestRepository testRepository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Test addTest(Test test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> getAllTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test getTestById(Long testId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test updateTest(Test test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTest(Long testId) {
		// TODO Auto-generated method stub
		
	}



}
