package com.blog.domain.user.presentation.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    USER_NOT_FOUND("유저를 찾을 수 없습니다");

    private final String message;

}
