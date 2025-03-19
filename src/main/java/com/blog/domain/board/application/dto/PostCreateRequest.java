package com.blog.domain.board.application.dto;

import java.util.List;

public record PostCreateRequest(
        String title,
        List<ContentDto> contents
) {
}
