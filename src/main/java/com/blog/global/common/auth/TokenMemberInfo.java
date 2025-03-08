package com.blog.global.common.auth;

import static com.blog.global.common.utils.jwt.JwtClaim.EMAIL;
import static com.blog.global.common.utils.jwt.JwtClaim.NICKNAME;

import io.jsonwebtoken.Claims;
import lombok.Builder;

@Builder
public record TokenMemberInfo(
    Long id,
    String email,
    String nickname
) {

  public static TokenMemberInfo from(Claims accessTokenClaims) {
    Long memberId = Long.valueOf(accessTokenClaims.getSubject());
    String email = accessTokenClaims.get(EMAIL.getClaim(), String.class);
    String nickname = accessTokenClaims.get(NICKNAME.getClaim(), String.class);

    return TokenMemberInfo.builder()
        .id(memberId)
        .email(email)
        .nickname(nickname)
        .build();
  }
}
