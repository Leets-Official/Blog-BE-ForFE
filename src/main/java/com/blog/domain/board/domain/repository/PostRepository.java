package com.blog.domain.board.domain.repository;

import com.blog.domain.board.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Page<Post> findAll(Pageable pageable);
}
