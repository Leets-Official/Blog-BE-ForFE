package com.blog.domain.auth.service;

import com.blog.domain.auth.dto.requests.LoginPostRequest;
import com.blog.domain.auth.dto.requests.RegisterPostRequest;
import com.blog.domain.auth.dto.responses.LoginPostResponse;
import com.blog.domain.auth.dto.responses.RegisterPostResponse;
import com.blog.domain.auth.exception.OAuth2RegisterFailureException;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.service.UserService;
import com.blog.global.common.oauth.MemberInfoFromProviders;
import com.blog.global.config.properties.AppConfigProperties;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service {
  // service
  private final AuthService authService;
  private final UserService userService;

  // utils
  private final AppConfigProperties appConfigProperties;

  @Transactional
  public LoginPostResponse oauth2Login(MemberInfoFromProviders memberInfoFromProviders) {
    Optional<User> getLoginAvailableResponse =
        this.userService.checkLoginAvailableByNickname(memberInfoFromProviders.nickname(),
            this.appConfigProperties.getOauthDummyPassword());

    if (getLoginAvailableResponse.isEmpty()) {
      RegisterPostRequest oauthRegister =
          RegisterPostRequest.createOauthRegister(
              memberInfoFromProviders.email(),
              memberInfoFromProviders.nickname(),
              this.appConfigProperties.getOauthDummyPassword(),
              memberInfoFromProviders.picture()
          );
      this.authService.register(oauthRegister);

      this.userService.checkLoginAvailable(oauthRegister.email(),
              this.appConfigProperties.getOauthDummyPassword())
          .orElseThrow(OAuth2RegisterFailureException::new);
    }

    LoginPostRequest loginPostRequest = LoginPostRequest.createOAuthLogin(
        memberInfoFromProviders.nickname(), this.appConfigProperties.getOauthDummyPassword());
    return this.authService.login(loginPostRequest, true);
  }
}
