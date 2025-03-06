package com.blog.domain.board.presentation.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    CREATE_SUCCESS("게시물 생성에 성공했습니다");

    private final String message;

}
