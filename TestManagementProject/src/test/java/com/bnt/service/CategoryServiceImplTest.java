package com.bnt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.entity.Categories;
import com.bnt.entity.CategoryResponse;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.repository.CategoryRepository;

@SpringBootTest
class CategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryService;

	public Categories setCategoryRequest() {
		Categories category = new Categories();
		category.setCategoryId(1L);
		category.setTitle("Spring Core");
		category.setDescription("This is Spring Core Category Created");

		return category;

	}

	@Test
	public void testAddNewCategory() {

		Categories inputCategory = setCategoryRequest();
		when(categoryRepository.save(any())).thenReturn(inputCategory);
		Categories result = categoryService.addNewCategory(inputCategory);
		assertEquals(inputCategory, result);
	}

	@Test
	public void testGetAllCategory() {

		Categories inputCategory = setCategoryRequest();
		List<Categories> categories = new ArrayList<>();
		categories.add(inputCategory);
		when(categoryRepository.findAll()).thenReturn(categories);
		List<CategoryResponse> result = categoryService.getAllCatogory();
		assertEquals(categories.size(), result.size());
	}

	@Test
	public void testGetCategoryById() {
		Long categoryId = 1L;
		Categories category = setCategoryRequest();
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
		CategoryResponse result = categoryService.getCategoryById(categoryId);
		assertEquals(categoryId, result.getCategoryId());
		assertEquals("Spring Core", result.getCategoryName());
		assertEquals("This is Spring Core Category Created", result.getDecription());
	}

	@Test
	public void testGetCategoryById_NotFound() {
		Long categoryId = 1L;
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryService.getCategoryById(categoryId);
		});
	}

	@Test
	public void testUpdateCategory() {

		Long categoryId = 1L;
		Categories inputCategory = new Categories();
		inputCategory.setCategoryId(categoryId);
		inputCategory.setTitle("UpdatedTitle");
		inputCategory.setDescription("UpdatedDescription");
		Categories existingCategory = setCategoryRequest();
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
		when(categoryRepository.save(any())).thenReturn(inputCategory);
		CategoryResponse result = categoryService.updateCategory(inputCategory);

		assertEquals(categoryId, result.getCategoryId());
		assertEquals(inputCategory.getTitle(), result.getCategoryName());
		assertEquals(inputCategory.getDescription(), result.getDecription());
	}

	@Test
	public void testUpdateCategory_NotFound() {
		Long categoryId = 1L;
		Categories inputCategory = new Categories();
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryService.updateCategory(inputCategory);
		});
	}

	@Test
	public void testDeleteCategory() {

		Long categoryId = 1L;
		when(categoryRepository.existsById(categoryId)).thenReturn(true);
		categoryService.deleteCategory(categoryId);

	}

	@Test
	public void testDeleteCategory_NotFound() {
		Long categoryId = 1L;
		when(categoryRepository.existsById(categoryId)).thenReturn(false);
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryService.deleteCategory(categoryId);
		});
	}

}
