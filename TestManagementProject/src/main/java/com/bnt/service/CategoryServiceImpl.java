package com.bnt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bnt.exception.CategoryNotFoundException;
import com.bnt.model.Categories;
import com.bnt.model.CategoryResponse;
import com.bnt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repository;
	
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository=repository;
	}
		
	@Override
	public Categories addNewCategory(Categories category) {
		Categories newCategory = new Categories();
        newCategory.setTitle(category.getTitle());
        newCategory.setDescription(category.getDescription());
		return this.repository.save(newCategory);
	}

	@Override
	public List<CategoryResponse> getAllCatogory() {
	    List<Categories> categories = repository.findAll();
	    List<CategoryResponse> categoryResponses = new ArrayList<>();

	    for (Categories category : categories) {
	        categoryResponses.add(category.toResponse());
	    }

	    return categoryResponses;
	}
	
	public CategoryResponse getCategoryById(Long categoryId) {
	    Categories category = repository.findById(categoryId)
	            .orElseThrow(() -> new CategoryNotFoundException("Category not found"+ categoryId));
	    return convertToCategoryResponse(category);
	}


	@Override
	public CategoryResponse updateCategory(Categories category) {
	    try {
	        Categories existingCategory = this.repository.findById(category.getCategoryId())
	                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

	        existingCategory.setTitle(category.getTitle());
	        existingCategory.setDescription(category.getDescription());

	        Categories updatedCategory = this.repository.save(existingCategory);
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


	private CategoryResponse convertToCategoryResponse(Categories category) {
	    CategoryResponse updateCategory = new CategoryResponse();
	    updateCategory.setCategoryId(category.getCategoryId());
	    updateCategory.setCategoryName(category.getTitle()); 
	    updateCategory.setDecription(category.getDescription());
	    return updateCategory;
	}

}
