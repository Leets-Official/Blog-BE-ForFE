package com.blog.domain.user.application.dto.response;

import com.blog.domain.user.domain.entity.User;

public record UserGetResponse(
    Long id,
    String email,
    String nickname,
    String profilePicture,
    String name,
    String birthDate,
    String introduction
) {

  public static UserGetResponse from(User user) {
    return new UserGetResponse(
        user.getId(),
        user.getEmail(),
        user.getNickname(),
        user.getProfilePicture(),
        user.getName(),
        user.getBirthDate().toString(),
        user.getIntroduction()
    );
  }
}
