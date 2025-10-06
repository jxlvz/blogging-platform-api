package com.jxlvz.bloggingplatformapi.post.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {
  @NotNull private Long id;

  @NotNull
  @Size(max = 255)
  private String title;

  @NotNull private String content;

  private String author;

  @NotNull private String category;

  @NotNull private Instant createdAt;

  private Instant updatedAt;
}
