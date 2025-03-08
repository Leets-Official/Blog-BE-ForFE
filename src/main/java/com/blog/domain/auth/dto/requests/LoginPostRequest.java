package com.blog.domain.auth.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "로그인 요청 DTO")
public record LoginPostRequest(
    @Schema(description = "사용자 이메일", example = "user@example.com")
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    String email,

    @Schema(description = "사용자 비밀번호", example = "password123!")
    @NotBlank(message = "비밀번호는 필수입니다.")
    String password
) {

    public static LoginPostRequest createOAuthLogin(String email, String password) {
        return new LoginPostRequest(email, password);
    }
}