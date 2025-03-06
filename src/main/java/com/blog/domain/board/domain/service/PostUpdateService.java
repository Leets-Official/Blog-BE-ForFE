package com.blog.domain.board.domain.service;

import com.blog.domain.board.application.dto.PostUpdateDto;
import com.blog.domain.board.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUpdateService {

    public void update(Post post, PostUpdateDto dto) {
        post.update(dto.title(), dto.content(), dto.image());
    }
}
