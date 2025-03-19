package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.constant.ContentType;
import com.blog.domain.board.domain.entity.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "게시글 콘텐츠 DTO")
public record ContentDto(
        @Schema(description = "콘텐츠 순서", example = "1")
        long contentOrder,
        @Schema(description = "콘텐츠 데이터", example = "게시물 내용")
        String content,
        @Schema(description = "콘텐츠 유형", example = "TEXT / IMAGE")
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
