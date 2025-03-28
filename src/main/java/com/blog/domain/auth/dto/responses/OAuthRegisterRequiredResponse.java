package com.blog.domain.auth.dto.responses;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.oauth.MemberInfoFromProviders;

public record OAuthRegisterRequiredResponse(
    String nickname,
    String picture
) implements OAuthLoginResponse{

  @Override
  public String getResponseMessage() {
    return ResponseMessage.OAUTH2_REGISTER_REQUIRED.getMessage();
  }

  public static OAuthRegisterRequiredResponse from(MemberInfoFromProviders memberInfoFromProviders) {
    return new OAuthRegisterRequiredResponse(
        memberInfoFromProviders.nickname(),
        memberInfoFromProviders.picture()
    );
  }
}
