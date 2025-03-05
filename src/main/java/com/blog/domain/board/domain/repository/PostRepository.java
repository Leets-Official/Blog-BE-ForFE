package com.blog.domain.board.domain.repository;

import com.blog.domain.board.domain.entity.Post;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
