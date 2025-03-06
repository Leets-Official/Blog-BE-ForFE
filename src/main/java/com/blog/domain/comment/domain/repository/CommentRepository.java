package com.blog.domain.comment.domain.repository;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.comment.domain.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);

    void deleteAllByPost(Post post);
}
