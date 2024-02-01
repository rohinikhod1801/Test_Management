package com.bnt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.exception.CategoryException;
import com.bnt.model.Category;
import com.bnt.model.CategoryResponse;
import com.bnt.service.CategoryService;


@RestController
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService service;
	
	@PostMapping("/insert")
	public Category addNewCategory(@RequestBody Category category) {

		Category addcategory=service.addNewCategory(category);
		logger.info("Added a new category: {}", category);
		return addcategory;

	}
	
	@GetMapping
	public List<CategoryResponse> getAllCategory() {
		
		List<CategoryResponse> showCategories=service.getAllCatogory();
		logger.info("Getting all categories: {}",showCategories.toString());
		return showCategories;
	}
	


	@GetMapping("/{category_id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("category_id") Long categoryId) {
		try {
			CategoryResponse category=service.getCategoryById(categoryId);
			logger.info("Getting category by ID: {}", category);
			return ResponseEntity.ok(category);
		} catch (CategoryException ex) {
			logger.error("Category not found with ID: {}", categoryId, ex);
			return ResponseEntity.status(404).body(ex.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody Category category) {
		try {
			
			CategoryResponse updatedCategory = service.updateCategory(category);
			
			return ResponseEntity.ok(updatedCategory);
		} catch (Exception e) {
			logger.error("Error occurred while updating category", e);
			throw new RuntimeException("Error occurred while updating category", e);
		}
	}

	@DeleteMapping("/{category_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("category_id") Long categoryId) {
		try {
			logger.info("Deleting category with ID: {}", categoryId);
			this.service.deleteCategory(categoryId);
			return ResponseEntity.ok().build();
		} catch (CategoryException ex) {
			logger.error("Category not found with ID: {}", categoryId, ex);
			return ResponseEntity.status(404).body(ex.getMessage());
		}

	}
	
	
}
