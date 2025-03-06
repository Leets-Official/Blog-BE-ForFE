package com.blog.domain.comment.exception;

import static com.blog.domain.comment.presentation.constant.ResponseMessage.COMMENT_NOT_FOUND;

import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends ApplicationException {
    public CommentNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), COMMENT_NOT_FOUND.getMessage());
    }
}
