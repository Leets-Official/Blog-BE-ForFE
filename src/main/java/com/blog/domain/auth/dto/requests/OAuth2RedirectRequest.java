package com.blog.domain.auth.dto.requests;

public record OAuth2RedirectRequest(
    String code,
    String scope,
    Integer authuser,
    String prompt
) {

}
