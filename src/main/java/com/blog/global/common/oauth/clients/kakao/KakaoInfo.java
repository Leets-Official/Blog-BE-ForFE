package com.blog.global.common.oauth.clients.kakao;

import com.blog.domain.auth.entity.enums.OAuthProvider;
import com.blog.global.common.oauth.MemberInfoFromProviders;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public record KakaoInfo(
    Long id,
    @JsonProperty("connected_at")
    String connectedAt,
    Properties properties,
    @JsonProperty("kakao_account")
    KakaoAccount kakaoAccount
) {
  public record Properties(
      String nickname,
      @JsonProperty("profile_image")
      String profileImage,
      @JsonProperty("thumbnail_image")
      String thumbnailImage
  ) {}

  public record KakaoAccount(
      @JsonProperty("profile_nickname_needs_agreement")
      boolean profileNicknameNeedsAgreement,
      @JsonProperty("profile_image_needs_agreement")
      boolean profileImageNeedsAgreement,
      Profile profile,
      @JsonProperty("has_email")
      boolean hasEmail,
      @JsonProperty("email_needs_agreement")
      boolean emailNeedsAgreement,
      @JsonProperty("is_email_valid")
      boolean isEmailValid,
      @JsonProperty("is_email_verified")
      boolean isEmailVerified,
      String email
  ) {}

  public record Profile(
      String nickname,
      @JsonProperty("thumbnail_image_url")
      String thumbnailImageUrl,
      @JsonProperty("profile_image_url")
      String profileImageUrl,
      @JsonProperty("is_default_image")
      boolean isDefaultImage,
      @JsonProperty("is_default_nickname")
      boolean isDefaultNickname
  ) {}

  public static MemberInfoFromProviders toMemberInfo(String jsonResponse, String accessToken) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      KakaoInfo kakaoInfo = objectMapper.readValue(jsonResponse, KakaoInfo.class);

      return new MemberInfoFromProviders(
          kakaoInfo.kakaoAccount().email,
          kakaoInfo.properties().nickname,
          kakaoInfo.properties().profileImage,
          accessToken,
          OAuthProvider.KAKAO
      );
    } catch (Exception e) {
      throw new RuntimeException("JSON 파싱 오류", e);
    }
  }
}