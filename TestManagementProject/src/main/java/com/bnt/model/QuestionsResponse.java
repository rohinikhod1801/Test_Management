package com.bnt.model;

public class QuestionsResponse {

	private Long questionId;
	private String content;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private String marks;

	public QuestionsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public QuestionsResponse(Long questionId, String content, String option1, String option2, String option3,
			String option4, String answer, String marks) {
		super();
		this.questionId = questionId;
		this.content = content;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.marks = marks;
	}


	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		if (marks == null || marks.trim().isEmpty()) {
	        throw new IllegalArgumentException("Content cannot be null or blank");
	    }
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "QuestionsResponse [questionId=" + questionId + ", content=" + content + ", option1=" + option1
				+ ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", answer=" + answer
				+ ", marks=" + marks + "]";
	}

}
