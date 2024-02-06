package com.bnt.model;

import java.util.List;

public class TestResponse {
	
	private String title;
    private String description;
    private int maxMarks;
    private int numberofQuestions;
    private boolean active;
    private List<QuestionsRequest> questionIds;
	public TestResponse() {
		super();
	}
	public TestResponse(String title, String description, int maxMarks, int numberofQuestions, boolean active,
			List<QuestionsRequest> questionIds) {
		super();
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberofQuestions = numberofQuestions;
		this.active = active;
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
	public List<QuestionsRequest> getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(List<QuestionsRequest> questionIds) {
		this.questionIds = questionIds;
	}
	@Override
	public String toString() {
		return "TestResponse [title=" + title + ", description=" + description + ", maxMarks=" + maxMarks
				+ ", numberofQuestions=" + numberofQuestions + ", active=" + active + ", questionIds=" + questionIds
				+ "]";
	}
}
