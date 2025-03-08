package com.blog.domain.auth.exception;

import com.blog.domain.auth.dto.ResponseMessage;
import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class JwtClaimNotBeNullException extends ApplicationException {

    public JwtClaimNotBeNullException() {
        super(HttpStatus.BAD_REQUEST.value(), ResponseMessage.JWT_TOKEN_CLAIM_NOT_BE_NULL.getMessage());
    }
}
