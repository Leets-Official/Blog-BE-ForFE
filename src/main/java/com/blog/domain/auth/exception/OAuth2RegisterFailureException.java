package com.blog.domain.auth.exception;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class OAuth2RegisterFailureException extends ApplicationException {

  public OAuth2RegisterFailureException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.OAUTH2_REGISTER_FAILURE.getMessage());
  }
}
