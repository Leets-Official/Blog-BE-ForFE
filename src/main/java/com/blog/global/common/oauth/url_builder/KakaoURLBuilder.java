package com.blog.global.common.oauth.url_builder;

import com.blog.global.config.oauth.KakaoOAuthConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoURLBuilder implements OAuth2URLBuilder {

  private final KakaoOAuthConfigProperties kakaoOAuthConfigProperties;

  @Override
  public String getAuthorizationUrl() {
    StringBuilder uriBuilder = new StringBuilder();
    uriBuilder.append(this.kakaoOAuthConfigProperties.getAuthorizationUri())
        .append("?")
        .append("client_id=").append(this.kakaoOAuthConfigProperties.getClientId())
        .append("&redirect_uri=").append(this.kakaoOAuthConfigProperties.getRedirectUri())
        .append("&response_type=code");

    return uriBuilder.toString();
  }

  @Override
  public String getTokenUrl(String code) {
    return this.kakaoOAuthConfigProperties.getAccessTokenUri();
  }

  @Override
  public String getUserInfoUrl(String accessToken) {
    return this.kakaoOAuthConfigProperties.getUserInfoEndpoint();
  }
}
