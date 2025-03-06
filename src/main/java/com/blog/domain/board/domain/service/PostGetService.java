package com.blog.domain.board.domain.service;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.repository.PostRepository;
import com.blog.domain.board.exception.PostNotFoundException;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostGetService {
    private final PostRepository postRepository;

    public Post find(UUID postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    public List<Post> findAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return postRepository.findAll(pageable).getContent();
    }
}
