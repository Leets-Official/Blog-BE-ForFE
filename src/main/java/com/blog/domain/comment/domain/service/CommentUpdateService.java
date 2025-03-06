package com.blog.domain.comment.domain.service;

import com.blog.domain.comment.application.dto.CommentUpdateDto;
import com.blog.domain.comment.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentUpdateService {
    public void update(Comment comment, CommentUpdateDto dto) {
        comment.updateContent(dto.content());
    }
}
