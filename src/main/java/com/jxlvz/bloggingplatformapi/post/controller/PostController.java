package com.jxlvz.bloggingplatformapi.post.controller;

import com.jxlvz.bloggingplatformapi.post.dto.PostRequestDTO;
import com.jxlvz.bloggingplatformapi.post.dto.PostResponseDTO;
import com.jxlvz.bloggingplatformapi.post.mapper.PostMapper;
import com.jxlvz.bloggingplatformapi.post.model.Post;
import com.jxlvz.bloggingplatformapi.post.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getPosts(
      @RequestParam(required = false) String term) {

    var response = this.postService.getPosts(term).stream().map(PostMapper::toDTO).toList();

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
    Post post = this.postService.getPostById(postId);
    PostResponseDTO response = PostMapper.toDTO(post);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO dto) {
    Post post = PostMapper.toModel(dto);
    Post saved = this.postService.createPost(post, dto.getCategoryId());
    PostResponseDTO response = PostMapper.toDTO(saved);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{postId}")
  public ResponseEntity<PostResponseDTO> updatePost(
      @Valid @RequestBody PostRequestDTO newPostDto, @PathVariable Long postId) {

    Post newPostData = PostMapper.toModel(newPostDto);
    Post updatedPost = this.postService.updatePost(postId, newPostData, newPostDto.getCategoryId());
    PostResponseDTO response = PostMapper.toDTO(updatedPost);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
    this.postService.deletePost(postId);

    return ResponseEntity.noContent().build();
  }
}
