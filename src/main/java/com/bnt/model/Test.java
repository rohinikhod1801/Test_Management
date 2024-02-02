package com.bnt.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="test")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long test_id;

	private String title;

	private String description;

	private int maxMarks;

	private int numberofQuestions;

	private boolean active=false;
	
	 @ManyToMany
	    @JoinTable(
	            name = "test_categories",
	            joinColumns = @JoinColumn(name = "test_id"),
	            inverseJoinColumns = @JoinColumn(name = "category_id")
	    )
	    private List<Category> categories;

	    @ManyToMany
	    @JoinTable(
	            name = "test_questions",
	            joinColumns = @JoinColumn(name = "test_id"),
	            inverseJoinColumns = @JoinColumn(name = "question_id")
	    )
	    private List<Questions> questions;

	public Test() {
		super();
	}

	public Test(Long test_id, String title, String description, int maxMarks, int numberofQuestions, boolean active,
			List<Category> categories, List<Questions> questions) {
		super();
		this.test_id = test_id;
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberofQuestions = numberofQuestions;
		this.active = active;
		this.categories = categories;
		this.questions = questions;
	}

	public Long getTest_id() {
		return test_id;
	}

	public void setTest_id(Long test_id) {
		this.test_id = test_id;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Test [test_id=" + test_id + ", title=" + title + ", description=" + description + ", maxMarks="
				+ maxMarks + ", numberofQuestions=" + numberofQuestions + ", active=" + active + ", categories="
				+ categories + ", questions=" + questions + "]";
	}	

}
