package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PostReadAllResponse(
        @Schema(description = "게시글 id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID postId,
        @Schema(description = "게시글 제목", example = "게시글 제목")
        String title,
        @Schema(implementation = ContentDto.class)
        List<ContentDto> contents,
        @Schema(description = "게시글 이미지", example = "true")
        Boolean isOwner
) {
    public static PostReadAllResponse toResponse(Post post, User user, List<ContentDto> contentDtos) {
        return PostReadAllResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .contents(contentDtos)
                .isOwner(user != null && post.getUser().equals(user))
                .build();
    }
}
