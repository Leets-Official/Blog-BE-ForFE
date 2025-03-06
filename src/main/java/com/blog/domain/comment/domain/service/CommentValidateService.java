package com.blog.domain.comment.domain.service;

import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.comment.domain.repository.CommentRepository;
import com.blog.domain.comment.exception.CommentAccessDeniedException;
import com.blog.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentValidateService {
    private final CommentRepository commentRepository;

    public void certificate(Comment comment, User user) {
        if (!comment.getUser().equals(user)) {
            throw new CommentAccessDeniedException();
        }
    }
}
