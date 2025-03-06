package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.user.domain.entity.User;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PostReadResponse(
        UUID postId,
        String title,
        String content,
        String image,
        Boolean isOwner
) {
    public static PostReadResponse toResponse(Post post, User user) {
        return PostReadResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .image(post.getImage())
                .isOwner(post.getUser().equals(user))
                .build();
    }
}
