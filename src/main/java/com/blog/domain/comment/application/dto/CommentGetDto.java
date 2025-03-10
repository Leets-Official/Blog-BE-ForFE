package com.blog.domain.comment.application.dto;

import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CommentGetDto(
        @Schema(description = "댓글 Id", example = "1")
        long commentId,
        @Schema(description = "댓글 내용", example = "댓글 내용")
        String content,
        @Schema(description = "작성자 닉네임", example = "작성자 닉네임")
        String nickName,
        @Schema(description = "댓글 소유 여부", example = "true")
        boolean isOwner
) {
    public static CommentGetDto toResponse(Comment comment, User user) {
        return CommentGetDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .nickName(user.getNickname())
                .isOwner(comment.getUser().equals(user))
                .build();
    }
}
