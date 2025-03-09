package com.blog.domain.auth.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "액세스 토큰 재발급 응답 DTO")
public record AccessTokenReissuePostResponse(
    @Schema(description = "새로 발급된 액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String accessToken,

    @Schema(description = "새로 발급된 리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String refreshToken
) {

  public static AccessTokenReissuePostResponse success(String accessToken, String refreshToken) {
    return new AccessTokenReissuePostResponse(accessToken, refreshToken);
  }

  public static AccessTokenReissuePostResponse ofNotFoundRefreshToken() {
    return new AccessTokenReissuePostResponse(null, null);
  }
}
