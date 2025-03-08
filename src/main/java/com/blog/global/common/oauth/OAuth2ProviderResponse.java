package com.blog.global.common.oauth;

public record OAuth2ProviderResponse(
    String access_token,
    String expires_in,
    String scope,
    String token_type
) {

}
