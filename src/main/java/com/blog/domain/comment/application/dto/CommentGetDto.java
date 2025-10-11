package com.blog.domain.comment.application.dto;

import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

@Schema(description = "댓글 DTO")
@Builder
public record CommentGetDto(
    @Schema(description = "댓글 Id", example = "1")
    long commentId,
    @Schema(description = "댓글 내용", example = "댓글 내용")
    String content,
    @Schema(description = "작성자 닉네임", example = "작성자 닉네임")
    String nickName,
    @Schema(description = "작성자 프로필 이미지 URL", example = "https://example.com/profile.jpg")
    String profileUrl,
    @Schema(description = "댓글 작성 시간")
    LocalDateTime createdAt,
    @Schema(description = "댓글 소유 여부", example = "true")
    boolean isOwner
) {
  public static CommentGetDto toResponse(Comment comment, User loginUser) {
    User commentAuthor = comment.getUser();
    return CommentGetDto.builder()
        .commentId(comment.getId())
        .content(comment.getContent())
        .nickName(commentAuthor.getNickname())
        .profileUrl(commentAuthor.getProfilePicture())
        .createdAt(comment.getCreatedAt())
        .isOwner(loginUser != null && commentAuthor.getId().equals(loginUser.getId()))
        .build();
  }
}

