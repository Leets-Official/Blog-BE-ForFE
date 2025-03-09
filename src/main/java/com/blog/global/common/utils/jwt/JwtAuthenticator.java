package com.blog.global.common.utils.jwt;

import com.blog.domain.auth.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticator {

  private final SecretKey accessKey;
  private final SecretKey refreshKey;

  public JwtAuthenticator(
      @Value("${jwt.access.secret}") String accessSecret,
      @Value("${jwt.refresh.secret}") String refreshSecret
  ) {
    this.refreshKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(refreshSecret.getBytes()));
    this.accessKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(accessSecret.getBytes()));
  }

  public void verifyAccessToken(String token) {
    try {
      Claims claims = Jwts.parser()
          .verifyWith(accessKey)
          .build()
          .parseSignedClaims(token)
          .getPayload();

      if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
        throw new ExpiredJwtException(null, claims, "만료된 토큰입니다.");
      }
    } catch (SignatureException e) {
      throw new InvalidTokenException();
    }
  }

  public void verifyRefreshToken(String token) {
    try {
      Claims claims = Jwts.parser()
          .verifyWith(refreshKey)
          .build()
          .parseSignedClaims(token)
          .getPayload();

      if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
        throw new ExpiredJwtException(null, claims, "만료된 토큰입니다.");
      }
    } catch (SignatureException e) {
      throw new InvalidTokenException();
    }
  }
}
