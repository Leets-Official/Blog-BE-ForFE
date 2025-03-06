package com.blog.domain.comment.exception;

import static com.blog.domain.comment.presentation.constant.ResponseMessage.COMMENT_ACCESS_DENIED;

import com.blog.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class CommentAccessDeniedException extends ApplicationException {
    public CommentAccessDeniedException() {
        super(HttpStatus.FORBIDDEN.value(), COMMENT_ACCESS_DENIED.getMessage());
    }
}
