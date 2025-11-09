package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PostSummaryResponse(
    UUID postId,
    String title,
    String nickName,
    String profileUrl,
    LocalDateTime createdAt,
    long commentCount,
    List<ContentDto> contents  // ✅ 추가!
) {
    public static PostSummaryResponse from(Post post) {
        long count = post.getComments() == null ? 0 : post.getComments().size();

        // ✅ ContentDto의 fromContent() 메서드 활용
        List<ContentDto> contentDtos = post.getContents() == null
            ? List.of()
            : post.getContents().stream()
                .map(ContentDto::fromContent)  // ✅ 기존 메서드 사용!
                .toList();

        return PostSummaryResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .nickName(post.getUser().getNickname())
            .profileUrl(post.getUser().getProfilePicture())
            .createdAt(post.getCreatedAt())
            .commentCount(count)
            .contents(contentDtos)  // ✅ 추가!
            .build();
    }
}
