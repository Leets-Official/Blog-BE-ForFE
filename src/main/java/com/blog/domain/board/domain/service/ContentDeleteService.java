package com.blog.domain.board.domain.service;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentDeleteService {
    private final ContentRepository contentRepository;

    public void deleteAllByPost(Post post) {
        contentRepository.deleteAllByPost(post);
    }
}
