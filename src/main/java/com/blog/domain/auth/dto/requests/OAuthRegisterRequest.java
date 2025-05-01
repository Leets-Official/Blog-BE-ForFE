package com.blog.domain.auth.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OAuthRegisterRequest(
    @Schema(description = "사용자 이메일", example = "user@example.com")
    @NotEmpty(message = "이메일은 필수 입력 사항입니다.")
    @Email(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @Schema(description = "사용자 닉네임 (영문, 숫자만 가능)", example = "john123")
    @NotEmpty(message = "닉네임은 공백이 아니어야 합니다.")
    @Size(min = 3, max = 20, message = "닉네임은 최소 3자 이상, 최대 20자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "닉네임은 영문, 숫자만 가능합니다.")
    String nickname,

    @Schema(description = "프로필 사진 URL", example = "https://example.com/profile.jpg")
    @Size(max = 1000, message = "프로필 사진의 길이가, 허용량을 초과했습니다.")
    String profilePicture,

    @Schema(description = "생년월일 (YYYY-MM-DD)", example = "2000-01-01")
    @NotNull(message = "생년월일은 필수 입력 사항입니다.")
    String birthDate,

    @Schema(description = "사용자 이름", example = "김주영")
    @Size(max = 20, message = "이름은 최대 20자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글만 가능합니다.")
    @NotEmpty(message = "이름은 공백이 아니어야 합니다.")
    String name,

    @Schema(description = "사용자 소개", example = "안녕하세요!")
    @Size(max = 30, message = "소개는 최대 30자 이하여야 합니다.")
    String introduction,

    @Schema(description = "redirect 응답에서 반환된 카카오 아이디")
    @NotNull
    Long kakaoId
) {

}
