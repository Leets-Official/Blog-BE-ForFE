package com.blog.domain.auth.controller;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.domain.auth.dto.requests.OAuthRegisterRequest;
import com.blog.domain.auth.dto.responses.LoginPostResponse;
import com.blog.domain.auth.dto.responses.OAuthLoginResponse;
import com.blog.domain.auth.dto.responses.RegisterPostResponse;
import com.blog.domain.auth.entity.enums.OAuthProvider;
import com.blog.domain.auth.service.OAuth2Service;
import com.blog.global.common.dto.ResponseDto;
import com.blog.global.common.oauth.MemberInfoFromProviders;
import com.blog.global.common.oauth.OAuth2AuthExecutor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/kakao")
  @Operation(summary = "OAuth2 로그인을 위한 redirect URL을 반환합니다.")
  public void getOAuthLogin(
      HttpServletResponse httpServletResponse
  ) throws IOException {
    OAuthProvider oAuthProviders = OAuthProvider.valueOf("KAKAO");
    String authorizationUrl = this.oAuth2AuthExecutor.getAuthorizationUrl(
        oAuthProviders.getOauthUrlBuilderClass());
    httpServletResponse.sendRedirect(authorizationUrl);
  }

  @GetMapping("/kakao/redirect")
  @Operation(summary = "AuthCode를 받아 OAuth2 로그인을 진행합니다.")
  public ResponseDto<OAuthLoginResponse> getOAuthLoginRedirect(
      @RequestParam("code") String code) {
    OAuthProvider oAuthProviders = OAuthProvider.valueOf("KAKAO");
    MemberInfoFromProviders memberInfoFromProviders =
        this.oAuth2AuthExecutor.getMemberInfoFrom(
          oAuthProviders.getOauth2ProviderClientClass(),
          code
        );

    OAuthLoginResponse loginInfo = this.oAuth2Service.oauth2Login(memberInfoFromProviders);

    return ResponseDto.of(
        loginInfo.getHttpStatus().value(),
        loginInfo.getResponseMessage(),
        loginInfo
    );
  }

  @PostMapping("/register-oauth")
  @Operation(summary = "OAuth2 회원가입을 진행합니다.")
  public ResponseDto<RegisterPostResponse> postOAuthRegister(
      @RequestBody @Valid OAuthRegisterRequest oAuthRegisterRequest
  ) {
    return ResponseDto.of(
        HttpStatus.CREATED.value(),
        ResponseMessage.REGISTER_SUCCESS.getMessage(),
        this.oAuth2Service.oauth2Register(oAuthRegisterRequest)
    );
  }
}
