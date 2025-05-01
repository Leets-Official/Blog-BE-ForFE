package com.blog.domain.auth.dto.responses;

import org.springframework.http.HttpStatus;

public interface OAuthLoginResponse {

  String getResponseMessage();
  HttpStatus getHttpStatus();
}
