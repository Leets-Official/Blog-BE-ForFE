package com.blog.domain.user.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProfilePicturePatchRequest(
    @Schema(description = "프로필 사진 URL", example = "https://example.com/profile.jpg")
    @Size(max = 1000, message = "프로필 사진의 길이가, 허용량을 초과했습니다.")
    @NotNull
    String profilePicture
) {

}
