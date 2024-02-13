package com.employee.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestManagementService {

	private static final String TEST_MANAGEMENT_URL = "http://localhost:8080/tests/";

    private final RestTemplate restTemplate;

    public TestManagementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Tests getTestForEmployee(Long employeeId) {
        return restTemplate.getForObject(TEST_MANAGEMENT_URL + employeeId, Tests.class);
    }
}
