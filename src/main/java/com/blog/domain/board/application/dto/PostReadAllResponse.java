package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.comment.application.dto.CommentGetDto;
import com.blog.domain.user.domain.entity.User;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PostReadAllResponse(
        UUID postId,
        String title,
        String content,
        String image,
        Boolean isOwner
) {
    public static PostReadAllResponse toResponse(Post post, User user) {
        return PostReadAllResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .image(post.getImage())
                .isOwner(post.getUser().equals(user))
                .build();
    }
}
