package com.blog.domain.comment.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CommentUpdateDto(
        @Schema(description = "댓글 내용", example = "변경할 댓글 내용")
        String content
) {
}
