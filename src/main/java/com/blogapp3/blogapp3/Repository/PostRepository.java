package com.blogapp3.blogapp3.Repository;

import com.blogapp3.blogapp3.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
