package com.blog.global.common.auth.guards;

public interface Guard {
    void canActivate() throws Exception;
}
