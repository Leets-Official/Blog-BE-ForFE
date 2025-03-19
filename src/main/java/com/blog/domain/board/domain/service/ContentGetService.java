package com.blog.domain.board.domain.service;

import com.blog.domain.board.domain.entity.Content;
import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.repository.ContentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentGetService {
    private final ContentRepository contentRepository;

    public List<Content> findAll(Post post) {
        return contentRepository.findAllByPostOrderByContentOrder(post);
    }
}
