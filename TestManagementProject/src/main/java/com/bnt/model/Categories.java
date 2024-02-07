package com.bnt.model;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long categoryId;

    private String title;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Questions> questions;

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.trim().isEmpty()) {
	        throw new IllegalArgumentException("Description cannot be null or blank");
	    }
		this.description = description;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", title=" + title + ", description=" + description
				+ ", questions=" + questions + "]";
	}

	public CategoryResponse toResponse() {
		return new CategoryResponse(categoryId,title,description);
	}

}
