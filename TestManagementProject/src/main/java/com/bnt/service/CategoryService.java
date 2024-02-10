package com.bnt.service;

import java.util.List;

import com.bnt.entity.Categories;
import com.bnt.entity.CategoryResponse;

public interface CategoryService {
	
	public Categories addNewCategory(Categories category);

	public List<CategoryResponse> getAllCatogory();

	public CategoryResponse getCategoryById(Long categoryId);

	public CategoryResponse updateCategory(Categories category);

	public void deleteCategory(Long categoryId);

	
	

}
