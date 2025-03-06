package com.blog.domain.board.domain.service;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.repository.PostRepository;
import com.blog.domain.board.exception.PostNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostGetService {
    private final PostRepository postRepository;

    public Post find(UUID postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }
}
