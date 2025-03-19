package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.constant.ContentType;

public record ContentDto(
        long contentOrder,
        String content,
        ContentType contentType
) {
}
