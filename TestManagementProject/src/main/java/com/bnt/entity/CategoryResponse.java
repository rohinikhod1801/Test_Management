package com.bnt.entity;

public class CategoryResponse {
	
	private Long categoryId;
    private String categoryName;
    private String decription;
	public CategoryResponse() {
		super();
	}
	public CategoryResponse(Long categoryId, String categoryName, String decription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.decription = decription;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	@Override
	public String toString() {
		return "CategoryResponse [categoryId=" + categoryId + ", categoryName=" + categoryName + ", decription="
				+ decription + "]";
	}
	

}
