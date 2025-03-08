package com.blog.domain.auth.entity.enums;

import com.blog.global.common.oauth.clients.OAuth2ProviderClient;
import com.blog.global.common.oauth.clients.kakao.KakaoProviderClient;
import com.blog.global.common.oauth.url_builder.KakaoURLBuilder;
import com.blog.global.common.oauth.url_builder.OAuth2URLBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OAuthProvider {
  KAKAO("kakao", KakaoURLBuilder.class, KakaoProviderClient.class),
  FACEBOOK("facebook", null, null);

  private final String name;
  private final Class<? extends OAuth2URLBuilder> oauthUrlBuilderClass;
  private final Class<? extends OAuth2ProviderClient> oauth2ProviderClientClass;
}
