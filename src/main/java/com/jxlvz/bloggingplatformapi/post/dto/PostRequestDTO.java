package com.jxlvz.bloggingplatformapi.post.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
  @NotNull
  @Size(min = 3, max = 255)
  private String title;

  @NotNull private String content;

  private String author;

  @NotNull private Long categoryId;
}
