package com.blog.domain.auth.exception;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class LoginFailureException extends ApplicationException {

    public LoginFailureException() {
        super(HttpStatus.UNAUTHORIZED.value(), ResponseMessage.LOGIN_FAILURE.getMessage());
    }
}
