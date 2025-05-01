package com.blog.domain.auth.dto.responses;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.oauth.MemberInfoFromProviders;
import org.springframework.http.HttpStatus;

public record OAuthRegisterRequiredResponse(
    String nickname,
    String picture,
    Long kakaoId
) implements OAuthLoginResponse{

  @Override
  public String getResponseMessage() {
    return ResponseMessage.OAUTH2_REGISTER_REQUIRED.getMessage();
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.UNAUTHORIZED;
  }

  public static OAuthRegisterRequiredResponse from(MemberInfoFromProviders memberInfoFromProviders) {
    return new OAuthRegisterRequiredResponse(
        memberInfoFromProviders.nickname(),
        memberInfoFromProviders.picture(),
        memberInfoFromProviders.id()
    );
  }
}
