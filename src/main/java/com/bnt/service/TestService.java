package com.bnt.service;

import java.util.List;

import com.bnt.model.Test;

public interface TestService {
	
	public Test addTest(Test test);

	public List<Test> getAllTest();

	public Test getTestById(Long testId);

	public Test updateTest(Test test);

	public void deleteTest(Long testId);
}
