package com.jxlvz.bloggingplatformapi.category.mapper;

import com.jxlvz.bloggingplatformapi.category.dto.CategoryRequestDTO;
import com.jxlvz.bloggingplatformapi.category.dto.CategoryResponseDTO;
import com.jxlvz.bloggingplatformapi.category.model.Category;

public class CategoryMapper {

  public static CategoryResponseDTO toDTO(Category category) {
    CategoryResponseDTO dto = new CategoryResponseDTO();
    dto.setId(category.getId());
    dto.setName(category.getName());

    return dto;
  }

  public static Category toModel(CategoryRequestDTO dto) {
    Category category = new Category();
    category.setName(dto.getName());

    return category;
  }
}
