package com.bnt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class QuestionsTest {

	@Test
	public void testQuestionsModel() {

		Categories category = new Categories();
		category.setCategoryId(1L);
		category.setTitle("Spring Core");
		category.setDescription("This is Spring Core Category Created");

		Questions question = new Questions();
		question.setContent("What is the largest mammal in the india");
		question.setOption1("African Elephant");
		question.setOption2("Blue Whale");
		question.setOption3("Giraffe");
		question.setOption4("Polar Bear");
		question.setAnswer("Blue Whale");
		question.setMarks("100");
		question.setCategory(category);

		assertEquals("What is the largest mammal in the india", question.getContent());
		assertEquals("African Elephant", question.getOption1());
		assertEquals("Blue Whale", question.getOption2());
		assertEquals("Giraffe", question.getOption3());
		assertEquals("Polar Bear", question.getOption4());
		assertEquals("Blue Whale", question.getAnswer());
		assertEquals("100", question.getMarks());
		assertNotNull(question.getCategory());
	}

	@Test
	public void testQuestionsModelWithNullContent() {
		try {
			Questions question = new Questions();
			question.setContent(null);
			question.setOption1("Blue Whale");
			fail("Expected IllegalArgumentException, but no exception was thrown");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testQuestionsModelWithBlankOption1() {
		try {
			Questions question = new Questions();
			question.setContent("What is the largest mammal in the india");
			question.setOption1("   "); // Blank option1
			fail("Expected IllegalArgumentException, but no exception was thrown");
		} catch (IllegalArgumentException e) {

		}
	}
	
	@Test
	public void testQuestionsModelWithNullCategory() {
		try {
			Questions question = new Questions();
			question.setContent("What is the largest mammal in the india");
			question.setOption1("Blue Whale");
			question.setCategory(null);
			fail("Expected IllegalArgumentException, but no exception was thrown");
		} catch (IllegalArgumentException e) {

		}
	}

}
