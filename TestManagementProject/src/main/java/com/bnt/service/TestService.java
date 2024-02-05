package com.bnt.service;

import java.util.List;

import com.bnt.model.TestManagement;
import com.bnt.model.TestResponse;

public interface TestService {
	
	public TestManagement addTest(TestManagement test);

	public List<TestResponse> getAllTest();

	public TestResponse getTestById(Long testId);

	public TestResponse updateTest(TestManagement test);

	public void deleteTest(Long testId);
}
