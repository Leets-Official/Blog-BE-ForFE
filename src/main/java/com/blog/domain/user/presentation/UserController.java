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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PatchMapping("/picture")
  @UseGuards({MemberGuard.class})
  public ResponseDto<Void> updatePicture(
      @RequestBody @Valid ProfilePicturePatchRequest request
  ) {
    this.userService.updatePicture(request.profilePicture());

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.PROFILE_PICTURE_UPDATED.getMessage());
  }

  @PatchMapping("/nickname")
  @UseGuards({MemberGuard.class})
  public ResponseDto<Void> updateNickname(
      @RequestBody @Valid NicknamePatchRequest request
  ) {
    this.userService.updateNickname(request.nickname());

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.NICKNAME_UPDATED.getMessage());
  }

  @PatchMapping("/password")
  @UseGuards({MemberGuard.class})
  public ResponseDto<Void> updatePassword(
      @RequestBody @Valid PasswordPatchRequest request
  ) {
    this.userService.updatePassword(request.password());

    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.PASSWORD_UPDATED.getMessage());
  }

  @GetMapping("/me")
  @UseGuards({MemberGuard.class})
  public ResponseDto<UserGetResponse> getMe() {
    return ResponseDto.of(HttpStatus.OK.value(), ResponseMessage.FOUND_MY_INFO_SUCCESS.getMessage(), this.userService.getMyInfo());
  }
}
