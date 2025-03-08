package com.blog.domain.user.presentation.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    USER_NOT_FOUND("유저를 찾을 수 없습니다"),
    PROFILE_PICTURE_UPDATED("프로필 사진이 업데이트 되었습니다"),
    NICKNAME_UPDATED("닉네임이 업데이트 되었습니다"),
    PASSWORD_UPDATED("비밀번호가 업데이트 되었습니다");

    private final String message;

}
