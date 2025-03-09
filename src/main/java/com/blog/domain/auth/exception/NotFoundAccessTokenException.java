package com.blog.domain.auth.exception;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NotFoundAccessTokenException extends ApplicationException {

  public NotFoundAccessTokenException() {
    super(HttpStatus.UNAUTHORIZED.value(), ResponseMessage.NOT_FOUND_ACCESS_TOKEN.getMessage());
  }
}
