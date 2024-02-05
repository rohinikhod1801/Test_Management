package com.bnt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestManagementTest {

	@Test
	public void testCreateTestWithValidData() {
		TestManagement test = new TestManagement();
		test.setTitle("Core Java Test");
		test.setDescription("Created Core Java Test");
		test.setMaxMarks(100);
		test.setNumberOfQuestions(20);
		test.setActive(true);

		assertEquals("Core Java Test", test.getTitle());
		assertEquals("Created Core Java Test", test.getDescription());
		assertEquals(100, test.getMaxMarks());
		assertEquals(20, test.getNumberOfQuestions());
		assertEquals(true, test.isActive());
	}

	@Test
	public void testCreateTestWithNullTitle() {
		try {
			TestManagement test = new TestManagement();
			test.setTitle(null);
			test.setDescription("Created Spring MVC Test");
			test.setMaxMarks(100);
			test.setNumberOfQuestions(20);
			test.setActive(true);
			
			fail("Expected IllegalArgumentException, but no exception was thrown");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testCreateTestWithBlankDescription() {
		try {
			TestManagement test = new TestManagement();
			test.setTitle("Hibernate");
			test.setDescription("   "); // Blank description
			test.setMaxMarks(100);
			test.setNumberOfQuestions(20);
			test.setActive(true);
			
			fail("Expected IllegalArgumentException, but no exception was thrown");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testCreateTestWithNegativeMaxMarks() {
		try {
			TestManagement test = new TestManagement();
			test.setTitle("JSP");
			test.setDescription("Created JSP Test");
			test.setMaxMarks(-50);
			test.setNumberOfQuestions(20);
			test.setActive(true);

			fail("Expected IllegalArgumentException, but no exception was thrown");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testCreateTestWithNegativeNumberOfQuestions() {
		try {
			TestManagement test = new TestManagement();
			test.setTitle("Angular");
			test.setDescription("Created Angular Test");
			test.setMaxMarks(100);
			test.setNumberOfQuestions(-10);
			test.setActive(true);

			fail("Expected IllegalArgumentException, but no exception was thrown");
		} catch (IllegalArgumentException e) {

		}
	}
}
