package com.jxlvz.bloggingplatformapi.post.service;

import com.jxlvz.bloggingplatformapi.category.model.Category;
import com.jxlvz.bloggingplatformapi.category.repository.CategoryRepository;
import com.jxlvz.bloggingplatformapi.common.exception.NotFoundException;
import com.jxlvz.bloggingplatformapi.post.model.Post;
import com.jxlvz.bloggingplatformapi.post.repository.PostRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;
  private final CategoryRepository categoryRepository;

  public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
    this.postRepository = postRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<Post> getPosts(String term) {
    if (term == null || term.isBlank()) {
      return postRepository.findAll();
    }
    String pattern = toLikePattern(term);

    return postRepository.searchByPattern(pattern);
  }

  private String toLikePattern(String input) {
    String escaped = input.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");

    return "%" + escaped.trim() + "%";
  }

  public Post getPostById(Long id) {
    return this.postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post", id));
  }

  public Post createPost(Post post, Long categoryId) {
    Category category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new NotFoundException("Category", categoryId));
    post.setCreatedAt(Instant.now());
    post.setCategory(category);

    return this.postRepository.save(post);
  }

  public Post updatePost(Long postId, Post newPostData, Long categoryId) {
    Post post = this.getPostById(postId);
    Category category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () -> new NotFoundException("Category", newPostData.getCategory().getId()));

    post.setTitle(newPostData.getTitle());
    post.setContent(newPostData.getContent());
    post.setAuthor(newPostData.getAuthor());
    post.setCategory(category);
    post.setUpdatedAt(Instant.now());

    return this.postRepository.save(post);
  }

  public void deletePost(Long postId) {
    if (!postRepository.existsById(postId)) {
      throw new NotFoundException("Post", postId);
    }
    postRepository.deleteById(postId);
  }

  public Boolean existsById(Long postId) {
    return this.postRepository.existsById(postId);
  }
}
