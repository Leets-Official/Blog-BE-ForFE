package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.user.domain.entity.User;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PostSummaryResponse(
    UUID postId,
    String title,
    String nickName,
    String profileUrl,
    LocalDateTime createdAt,
    Boolean isOwner,
    long commentCount
) {
    public static PostSummaryResponse toResponse(Post post, User user, long commentCount) {
        return PostSummaryResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .nickName(post.getUser().getNickname())
            .profileUrl(post.getUser().getProfilePicture())
            .createdAt(post.getCreatedAt())
            .isOwner(user != null && post.getUser().equals(user)) // isOwner 로직 추가
            .commentCount(commentCount)
            .build();
    }
}

