package com.jxlvz.bloggingplatformapi.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
  @NotBlank
  @Size(min = 3, max = 100)
  private String name;
}
