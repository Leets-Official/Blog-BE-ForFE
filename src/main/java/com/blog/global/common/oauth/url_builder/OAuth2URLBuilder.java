package com.blog.global.common.oauth.url_builder;

public interface OAuth2URLBuilder {

  String getAuthorizationUrl();
  String getTokenUrl(String code);
  String getUserInfoUrl(String accessToken);
}
