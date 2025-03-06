package com.blog.domain.board.exception;

import static com.blog.domain.board.presentation.constant.ResponseMessage.READ_FAILED;

import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ApplicationException {
    public PostNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), READ_FAILED.getMessage());
    }
}
