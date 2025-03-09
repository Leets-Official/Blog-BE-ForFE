package com.blog.domain.user.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NicknamePatchRequest(
    @Schema(description = "사용자 닉네임 (영문, 숫자만 가능)", example = "john123")
    @NotEmpty(message = "닉네임은 공백이 아니어야 합니다.")
    @Size(min = 3, max = 20, message = "닉네임은 최소 3자 이상, 최대 20자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "닉네임은 영문, 숫자만 가능합니다.")
    String nickname
) {

}
