package com.blog.domain.board.domain.service;

import com.blog.domain.board.application.dto.ContentDto;
import com.blog.domain.board.domain.entity.Content;
import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.repository.ContentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentSaveService {
    private final ContentRepository contentRepository;

    public void create(List<ContentDto>
                               contentDtos, Post post) {

        List<Content> contents = contentDtos.stream()
                .map(dto -> Content.fromDto(dto, post))
                .toList();

        contentRepository.saveAll(contents);
    }
}
