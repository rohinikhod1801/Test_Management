package com.bnt.service;

import java.util.List;

import com.bnt.entity.TestResponse;
import com.bnt.entity.Tests;

public interface TestService {
	
	public Tests addTest(Tests test);

	public List<TestResponse> getAllTest();

	public TestResponse getTestById(Long testId);

	public TestResponse updateTest(Tests test);

	public void deleteTest(Long testId);
}
