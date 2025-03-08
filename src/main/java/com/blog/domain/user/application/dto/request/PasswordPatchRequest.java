package com.blog.domain.user.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PasswordPatchRequest(
    @Schema(description = "비밀번호 (8~64자, 영문, 숫자, 특수문자 필수)", example = "Password123!")
    @NotEmpty(message = "비밀번호는 공백이 아니어야 합니다.")
    @Size(min = 8, max = 64, message = "비밀번호는 최소 8자 이상, 최대 64자 이하여야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$",
        message = "비밀번호 8~64자, 영문, 숫자, 특수문자가 필수입니다.")
    String password
) {

}
