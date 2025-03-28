package com.blog.domain.user.exception;

import com.blog.global.common.exception.ApplicationException;

public class NicknameDuplicateException extends ApplicationException {

    public NicknameDuplicateException() {
        super(409, "이미 사용중인 닉네임입니다.");
    }
}
