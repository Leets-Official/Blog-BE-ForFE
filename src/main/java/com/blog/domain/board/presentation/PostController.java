package com.blog.domain.board.presentation;

import static com.blog.domain.board.presentation.constant.ResponseMessage.CREATE_SUCCESS;
import static com.blog.domain.board.presentation.constant.ResponseMessage.DELETE_SUCCESS;
import static com.blog.domain.board.presentation.constant.ResponseMessage.READ_SUCCESS;
import static com.blog.domain.board.presentation.constant.ResponseMessage.UPDATE_SUCCESS;

import com.blog.domain.board.application.dto.PostCreateDto;
import com.blog.domain.board.application.dto.PostReadAllResponse;
import com.blog.domain.board.application.dto.PostReadResponse;
import com.blog.domain.board.application.dto.PostUpdateDto;
import com.blog.domain.board.application.usecase.PostManageUsecase;
import com.blog.global.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    @Operation(summary = "게시물 조회")
    public ResponseDto<PostReadResponse> read(@RequestHeader Long userId,
                                              @Parameter(description = "조회할 게시물 id", example = "62d0e871-500c-45f2-893a-4f90fee5da99") @RequestParam UUID postId) {
        PostReadResponse response = postManageUsecase.readPost(userId, postId);
        return ResponseDto.of(HttpStatus.OK.value(), READ_SUCCESS.getMessage(), response);
    }

    @GetMapping("/all")
    @Operation(summary = "게시물 리스트 조회")
    public ResponseDto<List<PostReadAllResponse>> readAll(@RequestHeader Long userId,
                                                          @Parameter(description = "페이지당 항목 수", example = "10") @RequestParam int size,
                                                          @Parameter(description = "조회할 페이지 번호", example = "1") @RequestParam int page) {
        List<PostReadAllResponse> response = postManageUsecase.readAllPost(userId, size, page);
        return ResponseDto.of(HttpStatus.OK.value(), READ_SUCCESS.getMessage(), response);
    }

    @PatchMapping()
    @Operation(summary = "게시물 업데이트")
    public ResponseDto<Void> update(@RequestHeader Long userId,
                                    @Parameter(description = "수정할 게시물 id", example = "62d0e871-500c-45f2-893a-4f90fee5da99") @RequestParam UUID postId,
                                    @RequestBody PostUpdateDto dto) {
        postManageUsecase.updatePost(userId, postId, dto);
        return ResponseDto.of(HttpStatus.OK.value(), UPDATE_SUCCESS.getMessage());
    }

    @DeleteMapping()
    @Operation(summary = "게시물 삭제")
    public ResponseDto<Void> delete(@RequestHeader Long userId,
                                    @Parameter(description = "삭제할 게시물 id", example = "62d0e871-500c-45f2-893a-4f90fee5da99") @RequestParam UUID postId) {
        postManageUsecase.deletePost(userId, postId);
        return ResponseDto.of(HttpStatus.OK.value(), DELETE_SUCCESS.getMessage());
    }
}
