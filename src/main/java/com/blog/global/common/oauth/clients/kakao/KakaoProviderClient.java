package com.blog.global.common.oauth.clients.kakao;

import com.blog.global.common.oauth.MemberInfoFromProviders;
import com.blog.global.common.oauth.OAuth2ProviderResponse;
import com.blog.global.common.oauth.clients.OAuth2ProviderClient;
import com.blog.global.common.oauth.url_builder.KakaoURLBuilder;
import com.blog.global.config.oauth.KakaoOAuthConfigProperties;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoProviderClient implements OAuth2ProviderClient {

  private final KakaoURLBuilder kakaoURLBuilder;
  private final KakaoOAuthConfigProperties kakaoOAuthConfigProperties;

  @Override
  public String getAccessToken(String authToken) {
    StringBuilder bodyBuilder = new StringBuilder();

    bodyBuilder.append("grant_type=authorization_code")
        .append("&client_id=").append(this.kakaoOAuthConfigProperties.getClientId())
        .append("&redirect_uri=").append(this.kakaoOAuthConfigProperties.getRedirectUri())
        .append("&code=").append(authToken);

    RestClient restClient = RestClient.create();

    OAuth2ProviderResponse oAuth2ProviderResponse =
        restClient.post()
                  .uri(this.kakaoURLBuilder.getTokenUrl(authToken))
                  .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                  .body(bodyBuilder.toString())
                  .retrieve()
                  .toEntity(OAuth2ProviderResponse.class)
                  .getBody();

    return oAuth2ProviderResponse.access_token();
  }

  @Override
  public MemberInfoFromProviders getMemberInfo(String accessToken) {
    RestClient restClient = RestClient.create();

    String jsonResponse = restClient.post()
        .uri(this.kakaoURLBuilder.getUserInfoUrl(accessToken))
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .toEntity(String.class)
        .getBody();

    return KakaoInfo.toMemberInfo(jsonResponse, accessToken);
  }
}
