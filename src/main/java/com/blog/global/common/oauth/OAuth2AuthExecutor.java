package com.blog.global.common.oauth;

import com.blog.global.common.oauth.clients.OAuth2ProviderClient;
import com.blog.global.common.oauth.url_builder.OAuth2URLBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2AuthExecutor {

  private final ApplicationContext applicationContext;

  public String getAuthorizationUrl(Class<? extends OAuth2URLBuilder> urlBuilderClass) {
    OAuth2URLBuilder urlBuilder = this.applicationContext.getBean(urlBuilderClass);
    return urlBuilder.getAuthorizationUrl();
  }

  public MemberInfoFromProviders getMemberInfoFrom(Class<? extends OAuth2ProviderClient> oauthProviderClientClass, String code) {
    OAuth2ProviderClient providerClient = this.applicationContext.getBean(oauthProviderClientClass);

    return providerClient.getMemberInfo(providerClient.getAccessToken(code));
  }
}
