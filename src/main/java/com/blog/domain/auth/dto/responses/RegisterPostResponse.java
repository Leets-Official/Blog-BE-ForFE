package com.blog.domain.auth.dto.responses;

import com.blog.domain.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원가입 응답 DTO")
public record RegisterPostResponse(
    @Schema(description = "등록된 사용자 이메일", example = "user@example.com")
    String email,
    
    @Schema(description = "등록된 사용자 닉네임", example = "john123")
    String nickname,
    
    @Schema(description = "등록된 프로필 사진 URL", example = "https://example.com/profile.jpg")
    String profilePicture,

    @Schema(description = "등록된 한줄 소개")
    String introduction
) {

    public static RegisterPostResponse of(User user) {
        return new RegisterPostResponse(
            user.getEmail(),
            user.getNickname(),
            user.getProfilePicture(),
            user.getIntroduction()
        );
    }
}
