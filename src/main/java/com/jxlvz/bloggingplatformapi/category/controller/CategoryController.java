package com.jxlvz.bloggingplatformapi.category.controller;

import com.jxlvz.bloggingplatformapi.category.dto.CategoryRequestDTO;
import com.jxlvz.bloggingplatformapi.category.dto.CategoryResponseDTO;
import com.jxlvz.bloggingplatformapi.category.mapper.CategoryMapper;
import com.jxlvz.bloggingplatformapi.category.model.Category;
import com.jxlvz.bloggingplatformapi.category.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
    List<Category> categories = this.categoryService.getAllCategories();
    List<CategoryResponseDTO> response = categories.stream().map(CategoryMapper::toDTO).toList();

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
    Category category = this.categoryService.getCategoryById(categoryId);
    CategoryResponseDTO response = CategoryMapper.toDTO(category);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<CategoryResponseDTO> createCategory(
      @Valid @RequestBody CategoryRequestDTO body) {

    Category category = CategoryMapper.toModel(body);
    Category saved = this.categoryService.createCategory(category);
    CategoryResponseDTO response = CategoryMapper.toDTO(saved);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryResponseDTO> updateCategory(
      @Valid @RequestBody CategoryRequestDTO body, @PathVariable Long categoryId) {

    Category category = CategoryMapper.toModel(body);
    Category updatedCategory = this.categoryService.updateCategory(category, categoryId);
    CategoryResponseDTO response = CategoryMapper.toDTO(updatedCategory);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
    this.categoryService.deleteCategory(categoryId);

    return ResponseEntity.noContent().build();
  }
}
