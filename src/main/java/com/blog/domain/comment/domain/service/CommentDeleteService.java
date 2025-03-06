package com.blog.domain.comment.domain.service;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentDeleteService {
    private final CommentRepository commentRepository;

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public void deleteAll(Post post) {
        commentRepository.deleteAllByPost(post);
    }
}
