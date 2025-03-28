package com.blog.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
  LOGIN_SUCCESS("로그인에 성공했습니다."),
  LOGIN_FAILURE("로그인에 실패했습니다."),
  CLAIMS_NOT_BE_NULL("토큰에 담긴 정보가 없습니다."),
  REGISTER_SUCCESS("회원가입에 성공했습니다."),
  INVALID_TOKEN("유효하지 않은 토큰입니다."),
  JWT_TOKEN_CLAIM_NOT_BE_NULL("토큰에 담긴 정보가 없습니다."),
  NOT_FOUND_ACCESS_TOKEN("Access Token을 찾을 수 없습니다."),
  NOT_FOUND_REFRESH_TOKEN("Refresh Token을 찾을 수 없습니다."),
  REISSUE_ACCESS_TOKEN_SUCCESS("액세스 토큰 재발급에 성공했습니다."),
  OAUTH2_REGISTER_FAILURE("OAuth2 회원가입에 실패했습니다."),
  OAUTH2_REGISTER_REQUIRED("OAuth2 회원가입이 필요합니다.");

  private final String message;
}
