package com.blog.domain.board.domain.service;

import com.blog.domain.board.application.dto.PostUpdateRequest;
import com.blog.domain.board.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUpdateService {

    public void update(Post post, PostUpdateRequest dto) {
        post.update(dto.title());
    }
}
