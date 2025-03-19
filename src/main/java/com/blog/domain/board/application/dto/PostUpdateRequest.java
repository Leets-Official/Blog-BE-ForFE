package com.blog.domain.board.application.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record PostUpdateRequest(
        @Schema(description = "게시글 제목", example = "블로그 게시물 제목")
        @NotEmpty(message = "게시글 제목은 비어있을 수 없습니다.")
        String title,
        @ArraySchema(arraySchema = @Schema(implementation = ContentDto.class))
        @NotEmpty(message = "게시글 내용은 비어있을 수 없습니다.")
        List<ContentDto> contents
) {
}
