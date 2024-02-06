package com.bnt.service;

import java.util.List;

import com.bnt.model.CategoryRequest;
import com.bnt.model.CategoryResponse;

public interface CategoryService {
	
	public CategoryRequest addNewCategory(CategoryRequest category);

	public List<CategoryResponse> getAllCatogory();

	public CategoryResponse getCategoryById(Long categoryId);

	public CategoryResponse updateCategory(CategoryRequest category);

	public void deleteCategory(Long categoryId);

	
	

}
