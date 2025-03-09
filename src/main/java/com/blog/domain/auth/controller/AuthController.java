package com.blog.domain.auth.controller;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.domain.auth.dto.requests.AccessTokenReissuePostRequest;
import com.blog.domain.auth.dto.requests.LoginPostRequest;
import com.blog.domain.auth.dto.requests.RegisterPostRequest;
import com.blog.domain.auth.dto.responses.AccessTokenReissuePostResponse;
import com.blog.domain.auth.dto.responses.LoginPostResponse;
import com.blog.domain.auth.dto.responses.RegisterPostResponse;
import com.blog.domain.auth.service.AuthService;
import com.blog.global.common.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseDto<RegisterPostResponse> postRegister(
      @RequestBody @Valid RegisterPostRequest registerPostRequest) {

    RegisterPostResponse registerPostResponse = this.authService.register(registerPostRequest);

    return ResponseDto.of(HttpStatus.CREATED.value(), ResponseMessage.REGISTER_SUCCESS.getMessage(), registerPostResponse);
  }

  @PostMapping("/login")
  public ResponseDto<LoginPostResponse> postLogin(
      @RequestBody @Valid LoginPostRequest loginPostRequest
  ) {
    LoginPostResponse loginPostResponse =
        this.authService.login(loginPostRequest, false);

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.LOGIN_SUCCESS.getMessage(), loginPostResponse);
  }

  @PostMapping("/reissue")
  public ResponseDto<AccessTokenReissuePostResponse> postAccessTokenReissue(
      @RequestBody @Valid AccessTokenReissuePostRequest accessTokenReissuePostRequest
  ) {
    AccessTokenReissuePostResponse accessTokenReissuePostResponse =
        this.authService.reissueAccessTokenAndRefreshToken(
            accessTokenReissuePostRequest.refreshToken()
        );

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.REISSUE_ACCESS_TOKEN_SUCCESS.getMessage(), accessTokenReissuePostResponse);
  }
}
