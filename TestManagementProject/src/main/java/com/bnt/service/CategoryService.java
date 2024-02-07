package com.bnt.service;

import java.util.List;

import com.bnt.model.Categories;
import com.bnt.model.CategoryResponse;

public interface CategoryService {
	
	public Categories addNewCategory(Categories category);

	public List<CategoryResponse> getAllCatogory();

	public CategoryResponse getCategoryById(Long categoryId);

	public CategoryResponse updateCategory(Categories category);

	public void deleteCategory(Long categoryId);

	
	

}
