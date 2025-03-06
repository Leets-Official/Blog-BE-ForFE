package com.blog.domain.board.presentation;

import static com.blog.domain.board.presentation.constant.ResponseMessage.CREATE_SUCCESS;

import com.blog.domain.board.application.dto.PostCreateDto;
import com.blog.domain.board.application.usecase.PostManageUsecase;
import com.blog.global.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "POST")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostManageUsecase postManageUsecase;

    @PostMapping
    @Operation(summary = "게시물 생성")
    public ResponseDto<Void> create(@RequestHeader Long userId, @RequestBody PostCreateDto dto) {
        postManageUsecase.createPost(userId, dto);
        return ResponseDto.of(HttpStatus.CREATED.value(), CREATE_SUCCESS.getMessage());
    }
}
