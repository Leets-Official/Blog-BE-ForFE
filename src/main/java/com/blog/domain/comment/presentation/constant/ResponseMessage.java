package com.blog.domain.comment.presentation.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    CREATE_SUCCESS("댓글 생성에 성공했습니다."),
    COMMENT_NOT_FOUND("댓글 조회에 실패했습니다.");

    private final String message;

}
