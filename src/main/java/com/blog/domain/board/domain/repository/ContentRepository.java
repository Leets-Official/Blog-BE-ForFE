package com.blog.domain.board.domain.repository;

import com.blog.domain.board.domain.entity.Content;
import com.blog.domain.board.domain.entity.Post;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, UUID> {

    List<Content> findAllByPostOrderByContentOrder(Post post);
}
