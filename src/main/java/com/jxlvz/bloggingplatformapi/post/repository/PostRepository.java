package com.jxlvz.bloggingplatformapi.post.repository;

import com.jxlvz.bloggingplatformapi.post.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(
    value = """
        select p.*
        from posts p
        left join categories c on c.id = p.category_id
        where (:pattern is null)
            or p.title ILIKE :pattern
            or p.content ILIKE :pattern
            or c.name ILIKE :pattern
        order by p.created_at desc
        """,
    nativeQuery = true
    )
    List<Post> searchByPattern(@Param("pattern") String pattern);
}
