package com.blog.domain.comment.application.dto;

import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.user.domain.entity.User;
import lombok.Builder;

@Builder
public record CommentGetDto(
        String content,
        String nickName,
        boolean isOwner
) {
    public static CommentGetDto toResponse(Comment comment, User user) {
        return CommentGetDto.builder()
                .content(comment.getContent())
                .nickName(user.getNickname())
                .isOwner(comment.getUser().equals(user))
                .build();
    }
}
