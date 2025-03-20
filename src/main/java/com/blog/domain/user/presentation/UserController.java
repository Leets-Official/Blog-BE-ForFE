package com.blog.domain.user.presentation;

import com.blog.domain.user.application.dto.request.NicknamePatchRequest;
import com.blog.domain.user.application.dto.request.PasswordPatchRequest;
import com.blog.domain.user.application.dto.request.ProfilePicturePatchRequest;
import com.blog.domain.user.application.dto.response.UserGetResponse;
import com.blog.domain.user.domain.service.UserGetService;
import com.blog.domain.user.domain.service.UserService;
import com.blog.domain.user.presentation.constant.ResponseMessage;
import com.blog.global.common.auth.annotations.UseGuards;
import com.blog.global.common.auth.guards.MemberGuard;
import com.blog.global.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "USER")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PatchMapping("/picture")
  @UseGuards({MemberGuard.class})
  @Operation(summary = "프로필 사진을 수정하는 API입니다.")
  public ResponseDto<Void> updatePicture(
      @RequestBody @Valid ProfilePicturePatchRequest request
  ) {
    this.userService.updatePicture(request.profilePicture());

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.PROFILE_PICTURE_UPDATED.getMessage());
  }

  @PatchMapping("/nickname")
  @UseGuards({MemberGuard.class})
  @Operation(summary = "닉네임을 수정하는 API입니다.")
  public ResponseDto<Void> updateNickname(
      @RequestBody @Valid NicknamePatchRequest request
  ) {
    this.userService.updateNickname(request.nickname());

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.NICKNAME_UPDATED.getMessage());
  }

  @PatchMapping("/password")
  @UseGuards({MemberGuard.class})
  @Operation(summary = "비밀번호를 수정하는 API입니다.")
  public ResponseDto<Void> updatePassword(
      @RequestBody @Valid PasswordPatchRequest request
  ) {
    this.userService.updatePassword(request.password());

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.PASSWORD_UPDATED.getMessage());
  }

  @GetMapping("/me")
  @UseGuards({MemberGuard.class})
  @Operation(summary = "내 정보를 조회하는 API입니다.")
  public ResponseDto<UserGetResponse> getMe() {
    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.FOUND_MY_INFO_SUCCESS.getMessage(), this.userService.getMyInfo());
  }
}
