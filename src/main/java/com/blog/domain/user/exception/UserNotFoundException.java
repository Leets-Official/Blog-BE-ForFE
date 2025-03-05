package com.blog.domain.user.exception;

import static com.blog.domain.user.presentation.constant.ResponseMessage.USER_NOT_FOUND;

import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(){super(HttpStatus.NOT_FOUND.value(),USER_NOT_FOUND.getMessage() );}
}
