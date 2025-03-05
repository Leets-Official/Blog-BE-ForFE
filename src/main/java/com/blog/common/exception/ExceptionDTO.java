package com.blog.common.exception;

public record ExceptionDTO(
        String requestURL,
        String source,
        String message,
        Object rejectedValue
) {}
