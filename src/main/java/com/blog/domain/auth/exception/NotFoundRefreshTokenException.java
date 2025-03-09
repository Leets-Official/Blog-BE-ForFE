package com.blog.domain.auth.exception;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NotFoundRefreshTokenException extends ApplicationException {

  public NotFoundRefreshTokenException() {
    super(HttpStatus.UNAUTHORIZED.value(), ResponseMessage.NOT_FOUND_REFRESH_TOKEN.getMessage());
  }
}
