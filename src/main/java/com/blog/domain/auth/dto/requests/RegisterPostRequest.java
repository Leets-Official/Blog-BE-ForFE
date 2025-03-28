package com.blog.domain.auth.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Schema(description = "회원가입 요청 DTO")
public record RegisterPostRequest(
    @Schema(description = "사용자 이메일", example = "user@example.com")
    @NotEmpty(message = "이메일은 필수 입력 사항입니다.")
    @Email(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @Schema(description = "사용자 닉네임 (영문, 숫자만 가능)", example = "john123")
    @NotEmpty(message = "닉네임은 공백이 아니어야 합니다.")
    @Size(min = 3, max = 20, message = "닉네임은 최소 3자 이상, 최대 20자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "닉네임은 영문, 숫자만 가능합니다.")
    String nickname,

    @Schema(description = "비밀번호 (8~64자, 영문, 숫자, 특수문자 필수)", example = "Password123!")
    @NotEmpty(message = "비밀번호는 공백이 아니어야 합니다.")
    @Size(min = 8, max = 64, message = "비밀번호는 최소 8자 이상, 최대 64자 이하여야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$",
        message = "비밀번호 8~64자, 영문, 숫자, 특수문자가 필수입니다.")
    String password,

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
    String introduction
) {

    public static RegisterPostRequest createOauthRegister(String email, String nickname, String password, String profilePicture) {
        if (email == null) {
            email = UUID.randomUUID().toString().replace("-", "") + "@leets.land";
        }
        return new RegisterPostRequest(email, nickname, password, profilePicture);
    }
}
