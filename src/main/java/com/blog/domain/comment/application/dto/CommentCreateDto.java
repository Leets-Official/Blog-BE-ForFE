package com.blog.domain.comment.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CommentCreateDto(
        @Schema(description = "댓글 내용", example = "작성할 댓글 내용")
        String content
) {
}
