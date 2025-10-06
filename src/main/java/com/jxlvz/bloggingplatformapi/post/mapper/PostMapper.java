package com.jxlvz.bloggingplatformapi.post.mapper;

import com.jxlvz.bloggingplatformapi.post.dto.PostRequestDTO;
import com.jxlvz.bloggingplatformapi.post.dto.PostResponseDTO;
import com.jxlvz.bloggingplatformapi.post.model.Post;

public class PostMapper {

  public static PostResponseDTO toDTO(Post post) {
    PostResponseDTO dto = new PostResponseDTO();
    dto.setId(post.getId());
    dto.setTitle(post.getTitle());
    dto.setContent(post.getContent());
    dto.setAuthor(post.getAuthor());
    dto.setCategory(post.getCategory().getName());
    dto.setCreatedAt(post.getCreatedAt());
    dto.setUpdatedAt(post.getUpdatedAt());

    return dto;
  }

  public static Post toModel(PostRequestDTO dto) {
    Post post = new Post();
    post.setTitle(dto.getTitle());
    post.setContent(dto.getContent());
    post.setAuthor(dto.getAuthor());

    return post;
  }
}
