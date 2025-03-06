package com.blog.domain.comment.domain.service;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.comment.domain.repository.CommentRepository;
import com.blog.domain.comment.exception.CommentNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentGetService {
    private final CommentRepository commentRepository;

    public Comment find(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    public List<Comment> findALlByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }
}
