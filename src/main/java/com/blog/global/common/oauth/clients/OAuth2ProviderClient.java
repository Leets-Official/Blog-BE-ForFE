package com.blog.global.common.oauth.clients;


import com.blog.global.common.oauth.MemberInfoFromProviders;

public interface OAuth2ProviderClient {

  String getAccessToken(String authToken);
  MemberInfoFromProviders getMemberInfo(String accessToken);
}
