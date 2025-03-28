package com.blog.domain.user.presentation.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    USER_NOT_FOUND("유저를 찾을 수 없습니다"),
    PROFILE_PICTURE_UPDATED("프로필 사진이 업데이트 되었습니다"),
    NICKNAME_UPDATED("닉네임이 업데이트 되었습니다"),
    PASSWORD_UPDATED("비밀번호가 업데이트 되었습니다"),
    FOUND_MY_INFO_SUCCESS("내 정보 조회에 성공했습니다"),
    USER_UPDATED("유저 정보가 업데이트 되었습니다");

    private final String message;

}
