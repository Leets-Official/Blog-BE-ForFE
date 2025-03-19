package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.constant.ContentType;
import com.blog.domain.board.domain.entity.Content;
import lombok.Builder;

@Builder
public record ContentDto(
        long contentOrder,
        String content,
        ContentType contentType
) {
    public static ContentDto fromContent(Content content) {
        return ContentDto.builder()
                .contentOrder(content.getContentOrder())
                .content(content.getContent())
                .contentType(content.getContentType())
                .build();
    }
}
