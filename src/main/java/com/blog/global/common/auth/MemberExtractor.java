package com.blog.global.common.auth;

import com.blog.domain.auth.exception.NotFoundAccessTokenException;
import com.blog.global.common.utils.jwt.JwtAuthenticator;
import com.blog.global.common.utils.jwt.JwtExtractor;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@RequiredArgsConstructor
public class MemberExtractor {


  private final JwtAuthenticator jwtAuthenticator;
  private final JwtExtractor jwtExtractor;

  public void extractMemberFromToken() {
    HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

    Optional<String> extractedAccessTokenFromCookie = extractTokenFromHeader(httpServletRequest);
    if (extractedAccessTokenFromCookie.isEmpty()) {
      throw new NotFoundAccessTokenException();
    }

    String accessToken = extractedAccessTokenFromCookie.get();
    this.jwtAuthenticator.verifyAccessToken(accessToken);

    MemberContext.setMember(
        TokenMemberInfo.from(
            this.jwtExtractor.parseAccessTokenPayloads(accessToken)
        )
    );
  }

  private Optional<String> extractTokenFromHeader(HttpServletRequest httpServletRequest) {
    try {
      return Optional.of(httpServletRequest.getHeader("Authorization").substring("Bearer ".length()));
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
