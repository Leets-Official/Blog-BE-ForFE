package com.blog.domain.auth.controller;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.domain.auth.dto.responses.LoginPostResponse;
import com.blog.domain.auth.entity.enums.OAuthProvider;
import com.blog.domain.auth.service.OAuth2Service;
import com.blog.global.common.dto.ResponseDto;
import com.blog.global.common.oauth.MemberInfoFromProviders;
import com.blog.global.common.oauth.OAuth2AuthExecutor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuth2Controller {

  // service
  private final OAuth2Service oAuth2Service;

  // utils
  private final OAuth2AuthExecutor oAuth2AuthExecutor;

  @GetMapping("/{provider}")
  @Operation(summary = "OAuth2 로그인을 위한 redirect URL을 반환합니다. provider에는 'kakao'가 들어갑니다.")
  public void getOAuthLogin(
      @PathVariable("provider") String provider,
      HttpServletResponse httpServletResponse
  ) throws IOException {
    OAuthProvider oAuthProviders = OAuthProvider.valueOf(provider.toUpperCase());
    String authorizationUrl = this.oAuth2AuthExecutor.getAuthorizationUrl(
        oAuthProviders.getOauthUrlBuilderClass());
    httpServletResponse.sendRedirect(authorizationUrl);
  }

  @GetMapping("/{provider}/redirect")
  @Operation(summary = "AuthCode를 받아 OAuth2 로그인을 진행합니다. provider에는 'kakao'가 들어갑니다.")
  public ResponseDto<LoginPostResponse> getOAuthLoginRedirect(
      @PathVariable("provider") String provider,
      @RequestParam("code") String code) {
    OAuthProvider oAuthProviders = OAuthProvider.valueOf(provider.toUpperCase());
    MemberInfoFromProviders memberInfoFromProviders =
        this.oAuth2AuthExecutor.getMemberInfoFrom(
          oAuthProviders.getOauth2ProviderClientClass(),
          code
        );

    LoginPostResponse loginInfo = this.oAuth2Service.oauth2Login(memberInfoFromProviders);

    return ResponseDto.of(
        HttpStatus.OK.value(),
        ResponseMessage.LOGIN_SUCCESS.getMessage(),
        loginInfo
    );
  }
}
