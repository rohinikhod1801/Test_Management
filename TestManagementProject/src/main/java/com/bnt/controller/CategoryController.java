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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.entity.Categories;
import com.bnt.entity.CategoryResponse;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService service;

	@PostMapping
	public Categories addNewCategory(@RequestBody Categories category) {

		Categories addcategory = service.addNewCategory(category);
		logger.info("Added a new category: {}", category);
		return addcategory;

	}

	@GetMapping
	public List<CategoryResponse> getAllCategory() {

		List<CategoryResponse> showCategories = service.getAllCatogory();
		logger.info("Getting all categories: {}", showCategories.toString());
		return showCategories;
	}

	@GetMapping("/{category_id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("category_id") Long categoryId) {
		try {
			CategoryResponse category = service.getCategoryById(categoryId);
			logger.info("Getting category by ID: {}", category);
			return ResponseEntity.ok(category);
		} catch (CategoryNotFoundException ex) {
			logger.error("Category not found with ID: {}", categoryId, ex);
			throw new CategoryNotFoundException("Error occurred while getById category" + ex);
		}
	}

	@PutMapping
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody Categories category) {
		try {
			CategoryResponse updatedCategory = service.updateCategory(category);
			return ResponseEntity.ok(updatedCategory);
		} catch (CategoryNotFoundException e) {
			logger.error("Error occurred while updating category", e);
			throw new CategoryNotFoundException("Error occurred while updating category" + e);
		}
	}

	@DeleteMapping("/{category_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("category_id") Long categoryId) {
		try {
			logger.info("Deleting category with ID: {}", categoryId);
			this.service.deleteCategory(categoryId);
			return ResponseEntity.ok().build();
		} catch (CategoryNotFoundException ex) {
			logger.error("Category not found with ID: {}", categoryId, ex);
			throw new CategoryNotFoundException("Error occurred while Deleting category" + ex);
		}

	}

}
