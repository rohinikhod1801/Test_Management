package com.bnt.model;

import java.util.List;

public class TestResponse {
	
	private String title;
    private String description;
    private int maxMarks;
    private int numberofQuestions;
    private boolean active;
    private List<Category> categoryIds;
    private List<Questions> questionIds;
	public TestResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestResponse(String title, String description, int maxMarks, int numberofQuestions, boolean active,
			List<Category> categoryIds, List<Questions> questionIds) {
		super();
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberofQuestions = numberofQuestions;
		this.active = active;
		this.categoryIds = categoryIds;
		this.questionIds = questionIds;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}
	public int getNumberofQuestions() {
		return numberofQuestions;
	}
	public void setNumberofQuestions(int numberofQuestions) {
		this.numberofQuestions = numberofQuestions;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<Category> getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(List<Category> categoryIds) {
		this.categoryIds = categoryIds;
	}
	public List<Questions> getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(List<Questions> questionIds) {
		this.questionIds = questionIds;
	}
	

}
