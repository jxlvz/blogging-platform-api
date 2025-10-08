package com.jxlvz.bloggingplatformapi.category.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  @Column(nullable = false, length = 100, unique = true)
  private String name;

  @Column(nullable = false)
  private Instant createdAt;

  private Instant updatedAt;
}
