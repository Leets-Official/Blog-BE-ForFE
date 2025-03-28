package com.blog.domain.auth.dto.responses;

import com.blog.domain.auth.dto.ResponseMessage;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "로그인 응답 DTO")
public record LoginPostResponse(
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String accessToken,
    
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String refreshToken,
    
    @Schema(description = "사용자 닉네임", example = "john123")
    String nickname,
    
    @Schema(description = "프로필 사진 URL", example = "https://example.com/profile.jpg")
    String profilePicture
) implements OAuthLoginResponse{

    @Override
    public String getResponseMessage() {
        return ResponseMessage.LOGIN_SUCCESS.getMessage();
    }
}