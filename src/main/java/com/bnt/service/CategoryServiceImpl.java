package com.bnt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.exception.CategoryNotFoundException;
import com.bnt.model.Category;
import com.bnt.model.CategoryResponse;
import com.bnt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repository;
		
	@Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }
	
	@Override
	public Category addNewCategory(Category category) {
		Category newCategory = new Category();
        newCategory.setTitle(category.getTitle());
        newCategory.setDescription(category.getDescription());
		return this.repository.save(newCategory);
	}

	@Override
    public List<CategoryResponse> getAllCatogory() {
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(Category::toDTO)
                .collect(Collectors.toList());
    }
	
	public CategoryResponse getCategoryById(Long categoryId) {
	    Category category = repository.findById(categoryId)
	            .orElseThrow(() -> new CategoryNotFoundException("Category not found"+ categoryId));
	    return convertToCategoryResponse(category);
	}


	@Override
	public CategoryResponse updateCategory(Category category) {
	    try {
	        Category existingCategory = this.repository.findById(category.getCategory_id())
	                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

	        existingCategory.setTitle(category.getTitle());
	        existingCategory.setDescription(category.getDescription());

	        Category updatedCategory = this.repository.save(existingCategory);
	        return convertToCategoryResponse(updatedCategory);
	    } catch (CategoryNotFoundException e) {
	        throw new CategoryNotFoundException("Failed to update category" + category.getCategory_id() + " not found");
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


	private CategoryResponse convertToCategoryResponse(Category category) {
	    CategoryResponse updateCategory = new CategoryResponse();
	    updateCategory.setCategoryId(category.getCategory_id());
	    updateCategory.setCategoryName(category.getTitle()); 
	    updateCategory.setDecription(category.getDescription());
	    return updateCategory;
	}

}
