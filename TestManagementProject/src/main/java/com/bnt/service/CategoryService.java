package com.bnt.service;

import java.util.List;

import com.bnt.model.Category;
import com.bnt.model.CategoryResponse;

public interface CategoryService {
	
	public Category addNewCategory(Category category);

	public List<CategoryResponse> getAllCatogory();

	public CategoryResponse getCategoryById(Long categoryId);

	public CategoryResponse updateCategory(Category category);

	public void deleteCategory(Long categoryId);

	
	

}
