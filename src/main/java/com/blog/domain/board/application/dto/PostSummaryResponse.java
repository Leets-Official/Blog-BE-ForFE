package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Post;
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
    long commentCount
) {
    public static PostSummaryResponse from(Post post) {
        // N+1 문제를 방지하기 위해 post.getComments().size() 대신
        // commentRepository.countByPost(post) 등을 사용하는 것을 고려해볼 수 있습니다.
        // 하지만 현재 구조에서는 size()를 사용하겠습니다.
        long count = post.getComments() == null ? 0 : post.getComments().size();

        return PostSummaryResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .nickName(post.getUser().getNickname())
            .profileUrl(post.getUser().getProfilePicture())
            .createdAt(post.getCreatedAt())
            .commentCount(count)
            .build();
    }
}

