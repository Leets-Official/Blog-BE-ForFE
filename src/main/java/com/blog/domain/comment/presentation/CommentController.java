package com.blog.domain.comment.presentation;


import static com.blog.domain.comment.presentation.constant.ResponseMessage.CREATE_SUCCESS;
import static com.blog.domain.comment.presentation.constant.ResponseMessage.DELETE_SUCCESS;
import static com.blog.domain.comment.presentation.constant.ResponseMessage.UPDATE_SUCCESS;

import com.blog.domain.comment.application.dto.CommentCreateDto;
import com.blog.domain.comment.application.dto.CommentUpdateDto;
import com.blog.domain.comment.application.usecase.CommentManageUsecase;
import com.blog.global.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "COMMENT")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentManageUsecase commentManageUsecase;

    @PostMapping("{postId}")
    @Operation(summary = "댓글 작성")
    public ResponseDto<Void> create(@RequestHeader Long userId, @PathVariable UUID postId,
                                    @RequestBody CommentCreateDto dto) {
        commentManageUsecase.createComment(userId, postId, dto);
        return ResponseDto.of(HttpStatus.CREATED.value(), CREATE_SUCCESS.getMessage());
    }

    @PatchMapping("{commentId}")
    @Operation(summary = "댓글 수정")
    public ResponseDto<Void> update(@RequestHeader Long userId, @PathVariable Long commentId,
                                    @RequestBody CommentUpdateDto dto) {
        commentManageUsecase.updateComment(userId, commentId, dto);
        return ResponseDto.of(HttpStatus.CREATED.value(), UPDATE_SUCCESS.getMessage());
    }

    @DeleteMapping("{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseDto<Void> delete(@RequestHeader Long userId, @PathVariable Long commentId) {
        commentManageUsecase.deleteComment(userId, commentId);
        return ResponseDto.of(HttpStatus.CREATED.value(), DELETE_SUCCESS.getMessage());
    }
}
