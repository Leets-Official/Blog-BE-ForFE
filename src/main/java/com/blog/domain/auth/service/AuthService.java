package com.blog.domain.auth.service;

import com.blog.domain.auth.dto.requests.LoginPostRequest;
import com.blog.domain.auth.dto.requests.RegisterPostRequest;
import com.blog.domain.auth.dto.responses.AccessTokenReissuePostResponse;
import com.blog.domain.auth.dto.responses.LoginPostResponse;
import com.blog.domain.auth.dto.responses.RegisterPostResponse;
import com.blog.domain.auth.exception.InvalidTokenException;
import com.blog.domain.auth.exception.LoginFailureException;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.service.UserService;
import com.blog.domain.user.exception.EmailDuplicateException;
import com.blog.domain.user.exception.NicknameDuplicateException;
import com.blog.global.common.utils.jwt.JwtAuthenticator;
import com.blog.global.common.utils.jwt.JwtExtractor;
import com.blog.global.common.utils.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  // services
  private final UserService userService;

  // utils
  private final JwtProvider jwtProvider;
  private final JwtAuthenticator jwtAuthenticator;
  private final JwtExtractor jwtExtractor;

  @Transactional
  public LoginPostResponse login(LoginPostRequest loginPostRequest, boolean isOAuth) {
    Optional<User> isLoginAvailable;
    if (isOAuth) {
      isLoginAvailable = this.userService.checkLoginAvailableByNickname(
          loginPostRequest.email(),
          loginPostRequest.password()
      );
    } else {
       isLoginAvailable = this.userService.checkLoginAvailable(
          loginPostRequest.email(),
          loginPostRequest.password()
      );
    }

    if (isLoginAvailable.isEmpty()) {
      throw new LoginFailureException();
    }

    User user = isLoginAvailable.get();

    String accessToken = this.jwtProvider.generateAccessToken(
        user.getId(),
        user.getEmail(),
        user.getNickname()
    );

    String refreshToken = this.jwtProvider.generateRefreshToken(user.getId());

    return new LoginPostResponse(
        accessToken,
        refreshToken,
        user.getNickname(),
        user.getProfilePicture()
    );
  }

  @Transactional
  public RegisterPostResponse register(RegisterPostRequest registerPostRequest) {
    boolean isEmailDuplicated = this.userService.checkEmailDuplicate(registerPostRequest.email());
    if (isEmailDuplicated) {
      throw new EmailDuplicateException();
    }

    boolean isNicknameDuplicate = this.userService.checkNicknameDuplicate(registerPostRequest.nickname());
    if (isNicknameDuplicate) {
      throw new NicknameDuplicateException();
    }

    String hashedPassword = this.userService.hashPassword(registerPostRequest.password());

    User user = User.create(registerPostRequest, hashedPassword);

    user = this.userService.save(user);

    return new RegisterPostResponse(
        user.getEmail(),
        user.getNickname(),
        user.getProfilePicture()
    );
  }

  public AccessTokenReissuePostResponse reissueAccessTokenAndRefreshToken(String refreshToken) {
    this.jwtAuthenticator.verifyRefreshToken(refreshToken);

    User user;
    try {
      Long userId = Long.valueOf(
          this.jwtExtractor.parseRefreshTokenPayloads(refreshToken).getSubject());
      user = this.userService.findById(userId);
    } catch (NumberFormatException e) {
      throw new InvalidTokenException();
    }

    return AccessTokenReissuePostResponse.success(
        this.jwtProvider.generateAccessToken(
            user.getId(),
            user.getEmail(),
            user.getNickname()
        ),
        this.jwtProvider.generateRefreshToken(user.getId())
    );
  }
}
