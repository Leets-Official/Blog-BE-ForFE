package com.blog.domain.board.exception;

import static com.blog.domain.board.presentation.constant.ResponseMessage.ACCESS_DENIED;

import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class PostAccessDeniedException extends ApplicationException {
    public PostAccessDeniedException() {
        super(HttpStatus.FORBIDDEN.value(), ACCESS_DENIED.getMessage());
    }
}
