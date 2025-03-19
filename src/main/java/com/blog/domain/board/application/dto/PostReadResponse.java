package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Content;
import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.comment.application.dto.CommentGetDto;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PostReadResponse(
        @Schema(description = "게시글 id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID postId,
        @Schema(description = "게시글 제목", example = "게시글 제목")
        String title,
        @Schema(description = "게시글 내용", example = "게시글 내용")
        List<ContentDto> contents,
        @Schema(description = "게시글 소유 여부", example = "true")
        Boolean isOwner,
        @Schema(description = "댓글", example =
                "[{\"commentId\": 1,\"content\": \"댓글 내용\", \"nickName\": \"닉네임"
                        + "\", \"isOwner\": true}]")
        List<CommentGetDto> comments
) {
    public static PostReadResponse toResponse(Post post, User user, List<Content> contents, List<Comment> comments) {
        List<CommentGetDto> dtos = comments.stream()
                .map(comment -> CommentGetDto.toResponse(comment, user))
                .toList();

        List<ContentDto> contentDtos = contents.stream()
                .map(ContentDto::fromContent)
                .toList();

        return PostReadResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .contents(contentDtos)
                .isOwner(user != null && post.getUser().equals(user))
                .comments(dtos)
                .build();
    }
}
