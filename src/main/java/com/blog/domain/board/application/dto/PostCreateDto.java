package com.blog.domain.board.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostCreateDto(
        @Schema(description = "게시글 제목", example = "블로그 게시물 제목")
        String title,
        @Schema(description = "게시글 내용", example = "블로그 게시물 내용")
        String content,
        @Schema(description = "게시글 이미지", example = "s3 이미지 주소")
        String image) {
}
