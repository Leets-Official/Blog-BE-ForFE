package com.blog.domain.user.exception;

import com.blog.global.common.exception.ApplicationException;

public class EmailDuplicateException extends ApplicationException {

    public EmailDuplicateException() {
        super(409, "이미 사용중인 이메일입니다.");
    }
}
