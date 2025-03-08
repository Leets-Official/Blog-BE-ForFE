package com.blog.global.common.utils.jwt;


import com.blog.domain.auth.exception.JwtClaimNotBeNullException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private final String accessValidity;
  private final String refreshValidity;

  private final SecretKey accessKey;
  private final SecretKey refreshKey;

  public JwtProvider(
      @Value("${jwt.access.secret}") String accessSecret,
      @Value("${jwt.access.validity}") String accessValidity,
      @Value("${jwt.refresh.secret}") String refreshSecret,
      @Value("${jwt.refresh.validity}") String refreshValidity) {
    this.refreshKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(refreshSecret.getBytes()));
    this.accessKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(accessSecret.getBytes()));

    this.accessValidity = accessValidity;
    this.refreshValidity = refreshValidity;
  }

  public String generateAccessToken(
      Long id,
      String email,
      String nickname
  ) {
    return Jwts.builder()
        .subject(String.valueOf(id))
        .claims(this.getClaims(email, nickname))
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + Long.parseLong(accessValidity)))
        .signWith(this.accessKey, Jwts.SIG.HS256)
        .compact();
  }

  public String generateRefreshToken(Long memberId) {
    return Jwts.builder()
        .subject(String.valueOf(memberId))
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(
            System.currentTimeMillis() + Long.parseLong(refreshValidity)
        ))
        .signWith(refreshKey, Jwts.SIG.HS256)
        .compact();
  }

  private Map<String, String> getClaims(
      String email,
      String nickname
  ) {
    if (email == null || nickname == null) {
      throw new JwtClaimNotBeNullException();
    }

    return Map.of(
        JwtClaim.EMAIL.getClaim(), email,
        JwtClaim.NICKNAME.getClaim(), nickname
    );
  }
}
