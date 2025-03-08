package com.blog.domain.auth.exception;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends ApplicationException {

    public InvalidTokenException() {
        super(HttpStatus.UNAUTHORIZED.value(), ResponseMessage.INVALID_TOKEN.getMessage());
    }
}
