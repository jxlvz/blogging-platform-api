package com.jxlvz.bloggingplatformapi.category.service;

import com.jxlvz.bloggingplatformapi.category.model.Category;
import com.jxlvz.bloggingplatformapi.category.repository.CategoryRepository;
import com.jxlvz.bloggingplatformapi.common.exception.NotFoundException;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAllCategories() {
    return this.categoryRepository.findAll();
  }

  public Category getCategoryById(Long id) {
    return this.categoryRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Category", id));
  }

  public Category createCategory(Category category) {
    category.setName(category.getName().trim());
    category.setCreatedAt(Instant.now());

    return this.categoryRepository.save(category);
  }

  public Category updateCategory(Category updatedCategory, Long id) {
    Category category =
        this.categoryRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Category", id));
    category.setName(updatedCategory.getName().trim());
    category.setUpdatedAt(Instant.now());

    return this.categoryRepository.save(category);
  }

  public void deleteCategory(Long id) {
    if (!categoryRepository.existsById(id)) throw new NotFoundException("Category", id);
    this.categoryRepository.deleteById(id);
  }
}
