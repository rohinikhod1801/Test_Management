package com.bnt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.exception.CategoryNotFoundException;
import com.bnt.model.CategoryRequest;
import com.bnt.model.CategoryResponse;
import com.bnt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;
		
	@Override
	public CategoryRequest addNewCategory(CategoryRequest category) {
		CategoryRequest newCategory = new CategoryRequest();
        newCategory.setTitle(category.getTitle());
        newCategory.setDescription(category.getDescription());
		return this.repository.save(newCategory);
	}

	@Override
	public List<CategoryResponse> getAllCatogory() {
	    List<CategoryRequest> categories = repository.findAll();
	    List<CategoryResponse> categoryResponses = new ArrayList<>();

	    for (CategoryRequest category : categories) {
	        categoryResponses.add(category.toResponse());
	    }

	    return categoryResponses;
	}
	
	public CategoryResponse getCategoryById(Long categoryId) {
	    CategoryRequest category = repository.findById(categoryId)
	            .orElseThrow(() -> new CategoryNotFoundException("Category not found"+ categoryId));
	    return convertToCategoryResponse(category);
	}


	@Override
	public CategoryResponse updateCategory(CategoryRequest category) {
	    try {
	        CategoryRequest existingCategory = this.repository.findById(category.getCategoryId())
	                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

	        existingCategory.setTitle(category.getTitle());
	        existingCategory.setDescription(category.getDescription());

	        CategoryRequest updatedCategory = this.repository.save(existingCategory);
	        return convertToCategoryResponse(updatedCategory);
	    } catch (CategoryNotFoundException e) {
	        throw new CategoryNotFoundException("Failed to update category" + category.getCategoryId() + " not found");
	    }
	}
	@Override
	public void deleteCategory(Long categoryId) {
	    if (repository.existsById(categoryId)) {
	        try {
	        	repository.deleteById(categoryId);
	        } catch (CategoryNotFoundException e) {
	            throw new CategoryNotFoundException("Failed to delete category");
	        }
	    } else {
	        throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
	    }
	}


	private CategoryResponse convertToCategoryResponse(CategoryRequest category) {
	    CategoryResponse updateCategory = new CategoryResponse();
	    updateCategory.setCategoryId(category.getCategoryId());
	    updateCategory.setCategoryName(category.getTitle()); 
	    updateCategory.setDecription(category.getDescription());
	    return updateCategory;
	}

}
