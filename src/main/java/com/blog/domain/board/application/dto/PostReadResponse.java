package com.blog.domain.board.application.dto;

import com.blog.domain.board.domain.entity.Content;
import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.comment.application.dto.CommentGetDto;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PostReadResponse(
    @Schema(description = "게시글 id", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    UUID postId,
    @Schema(description = "게시글 제목", example = "게시글 제목")
    String title,
    @ArraySchema(arraySchema = @Schema(implementation = ContentDto.class))
    List<ContentDto> contents,
    @Schema(description = "게시글 소유 여부", example = "true")
    Boolean isOwner,
    @ArraySchema(arraySchema = @Schema(implementation = CommentGetDto.class))
    List<CommentGetDto> comments,
    @Schema(description = "작성자 닉네임", example = "멋쟁이프론트")
    String nickName,
    @Schema(description = "작성자 프로필 사진 url", example = "url")
    String profileUrl,
    @Schema(description = "작성자 한 줄 소개", example = "안녕하세요. 반갑습니다.")
    String introduction,
    @Schema(description = "게시글 작성일자", implementation = LocalDateTime.class)
    LocalDateTime createdAt
) {
  public static PostReadResponse toResponse(Post post, User user, List<Content> contents, List<Comment> comments) {
    List<CommentGetDto> dtos = comments.stream()
        .map(comment -> CommentGetDto.toResponse(comment, user))
        .toList();

    List<ContentDto> contentDtos = contents.stream()
        .map(ContentDto::fromContent)
        .toList();

    User postAuthor = post.getUser();
    return PostReadResponse.builder()
        .postId(post.getId())
        .title(post.getTitle())
        .contents(contentDtos)
        .isOwner(user != null && postAuthor.getId().equals(user.getId()))
        .nickName(postAuthor.getNickname())
        .profileUrl(postAuthor.getProfilePicture())
        .introduction(postAuthor.getIntroduction())
        .createdAt(post.getCreatedAt())
        .comments(dtos)
        .build();
  }
}

