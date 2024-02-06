package com.bnt.service;

import java.util.List;

import com.bnt.model.TestRequest;
import com.bnt.model.TestResponse;

public interface TestService {
	
	public TestRequest addTest(TestRequest test);

	public List<TestResponse> getAllTest();

	public TestResponse getTestById(Long testId);

	public TestResponse updateTest(TestRequest test);

	public void deleteTest(Long testId);
}
